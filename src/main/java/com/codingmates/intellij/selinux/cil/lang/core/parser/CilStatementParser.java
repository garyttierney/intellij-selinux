package com.codingmates.intellij.selinux.cil.lang.core.parser;

import com.codingmates.intellij.selinux.cil.CilBundle;
import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementType;
import com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilOperator;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import static com.codingmates.intellij.selinux.cil.CilBundle.message;
import static com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes.*;
import static com.codingmates.intellij.selinux.cil.lang.core.CilTypes.*;
import static com.codingmates.intellij.selinux.cil.lang.core.parser.CilParserUtil.*;

final class CilStatementParser {

    private final CilTopLevelElementTypeMap topLevelElementMap;
    private final PsiBuilder builder;

    CilStatementParser(CilTopLevelElementTypeMap topLevelElementMap, PsiBuilder builder) {
        this.topLevelElementMap = topLevelElementMap;
        this.builder = builder;
    }

    /**
     * Parse a macro {@code call} statement, with a form of:
     * <pre>
     * (call &lt;macro_id&gt; (&lt;argument...&gt;))
     * </pre>
     */
    private void parseCall() throws CilParserException {
        parseReferenceExpression();
        parseCallArgumentList();
    }

    /**
     * Parser a call argument list.  Allow any expression as a valid argument, and later identify type errors
     * when macro declaration information is available.
     */
    private void parseCallArgumentList() throws CilParserException {
        Marker marker = builder.mark();

        try {
            expect(builder, LPAREN, "parser.error.expected_lparen");

            while (builder.getTokenType() != RPAREN) {
                try {
                    parseExpression(true, false, ExpressionParseHint.NONE);
                } catch (CilParserException ex) {
                    break;
                }
            }

            expect(builder, RPAREN, "parser.error.expected_rparen");
            marker.done(CALL_ARG_LIST);
        } catch (CilParserException ex) {
            marker.drop();
            throw ex;
        }
    }

    /**
     * Parse a {@code classmapping} statement, in the form of
     * <pre>
     * (classmapping &lt;class_map&gt; &lt;id&gt; &lt;access_vectors&gt;)
     * </pre>
     * <p> Where {@code access_vectors} can be a previously declared {@code classpermissionset} or
     * an access vector expression.
     */
    private void parseClassMapping() throws CilParserException {
        parseReferenceExpression();
        parseReferenceExpression();
        parseSymbolAndListExpression(true, false, ExpressionParseHint.ACCESS_VECTOR);
    }

    /**
     * Parse a {@code constrain} or {@code mlsconstrain} statement, in the form of
     * <pre>
     * (constrain|mlsconstrain &lt;access_vectors&gt;
     *    &lt;expression...&gt;)
     * </pre>
     */
    private void parseConstrain() throws CilParserException {
        parseSymbolAndListExpression(true, false, ExpressionParseHint.ACCESS_VECTOR);
        parseExpression(false, false, ExpressionParseHint.CONSTRAIN);
    }

    /**
     * Parse a {@code block}, {@code in}, or {@code optional} container statement.
     * <pre>
     * (block|in|optional
     *      &lt;statement...&gt;)
     * </pre>
     *
     * @param nameIsReference {@code true} if the name of this container is a reference to a
     *                        previous declaration (i.e., an <code>(in &lt;reference&gt;)</code> expression.
     */
    private void parseContainer(boolean nameIsReference) throws CilParserException {
        if (nameIsReference) {
            parseReferenceExpression();
        } else {
            parseSymbol("parser.error.expected_identifier");
        }

        parseStatements(TokenSet.create(RPAREN));
    }

    /**
     * Parse a security {@code context} declaration statement with a form of:
     * <pre>
     * (context &lt;id&gt; (&lt;user_id&gt; &lt;role_id&gt; &lt;type_id&gt;
     * &lt;level_range_expr&gt;))
     * </pre>
     */
    private void parseContextDeclaration() throws CilParserException {
        parseSymbol("parser.error.expected_identifier");
        parseContextExpression(false);
    }

    /**
     * Parse a security context expression, optionally permitting an identifier depending on {@code
     * permitReference}.
     * <pre>
     * &lt;user_id&gt; &lt;role_id&gt; &lt;type_id&gt; &lt;level_range_expr&gt;
     * </pre>
     *
     * @param permitReference {@code true} to disable treating references as errors.
     */
    private void parseContextExpression(boolean permitReference) throws CilParserException {
        if (permitReference && atReference(builder)) {
            parseReferenceExpression();
            return;
        }

        PsiBuilder.Marker marker = builder.mark();
        try {
            expect(builder, LPAREN, "parser.error.expected_lparen");

            for (int i = 0; i < 3; i++) {
                parseReferenceExpression();
            }

            parseLevelRangeExpression(true);
            expect(builder, RPAREN, "parser.error.expected_rparen");
            marker.done(CONTEXT_EXPR);
        } catch (CilParserException ex) {
            marker.drop();
            throw ex;
        }
    }

    /**
     * Parse a CIL expression, allowing for the following types of expressions used in CIL policy:
     * <ul> <li>Binary expressions for booleans, sets, and constrain expressions</li> <li>Unary
     * expressions for boolean and set expressions</li> <li>Symbol and list expressions (a symbol
     * followed by a list of expressions), for access vectors and security levels</li> <li> String
     * literals for macro arguments.  Only if {@code allowQuotedString} is {@code true}. </li>
     * <li>Symbol references</li> </ul>
     *
     * @param allowQuotedString      A flag that disables treating quoted strings as errors.
     * @param literalOrReferenceOnly {@code true} to only parse literal and reference expressions.
     * @param parseHint              A parse hint given used to return a contextual error.
     * @throws CilParserException If an error was encountered parsing the expression.
     */
    private void parseExpression(boolean allowQuotedString, boolean literalOrReferenceOnly,
                                 ExpressionParseHint parseHint) throws CilParserException {
        IElementType tokenType = builder.getTokenType();

        if (!literalOrReferenceOnly && tokenType == LPAREN) {
            Marker exprMarker = builder.mark();
            builder.advanceLexer();

            try {
                expect(builder, IDENTIFIER, parseHint.expressionIdentifierError, false);
                CilOperator operator = CilOperator.from(builder.getTokenText());

                if (operator == CilOperator.INVALID) {
                    boolean isSymbolAndListExpression =
                            builder.lookAhead(1) == CilTokenTypes.LPAREN;

                    if (isSymbolAndListExpression) {
                        exprMarker.rollbackTo();
                        parseSymbolAndListExpression(false, false, parseHint);
                        return;
                    }

                    while (!builder.eof() && builder.getTokenType() != RPAREN) {
                        parseReferenceExpression();
                    }

                    expect(builder, RPAREN, "parser.error.expected_rparen");
                    exprMarker.done(LIST_EXPR);
                } else {
                    builder.remapCurrentToken(OPERATOR);
                    builder.advanceLexer();
                    parseExpression(false, false, parseHint);

                    boolean unaryOperator = operator.isUnary();
                    if (!unaryOperator) {
                        parseExpression(false, false, parseHint);
                    }

                    expect(builder, RPAREN, "parser.error.expected_rparen");
                    exprMarker.done(unaryOperator ? UNARY_EXPR : BINARY_EXPR);
                }
            } catch (CilParserException ex) {
                exprMarker.drop();
                throw ex;
            }
        } else if (tokenType == STRING && allowQuotedString) {
            parseStringLiteral("Unexpected error");
        } else if (atReference(builder)) {
            parseReferenceExpression();
        } else {
            if (allowQuotedString) {
                throw parserError("parser.error.expected_expression_allow_string");
            } else {
                throw parserError("parser.error.expected_expression_disallow_string");
            }
        }
    }

    /**
     * Parse a {@code filecon} statement representing a file context specification, with a form of:
     * <pre>
     * (filecon [&lt;base_path&gt;] &lt;regex&gt; &lt;file_type&gt; &lt;context_expr&gt;)
     * </pre>
     */
    private void parseFileContext() throws CilParserException {
        parseStringLiteral("parser.error.expected_quoted_string"); // regexp or base path

        if (builder.getTokenType() == STRING) {
            // if a second string is specified,
            // the first is the base path
            parseStringLiteral("Unexpected error");
        }

        expect(builder, IDENTIFIER, "parser.error.file_context_filetype_expected");

        // an empty context is equivalent to setting a context of <<none>>
        if (builder.getTokenType() == LPAREN && builder.lookAhead(1) == RPAREN) {
            builder.advanceLexer();
            builder.advanceLexer();
            return;
        }

        parseContextExpression(true);
    }

    private void parseLevelDeclaration() throws CilParserException {
        parseSymbol("parser.error.expected_identifier");
        parseSymbolAndListExpression(false, true, ExpressionParseHint.SECURITY_LEVEL);
    }

    private void parseLevelRangeDeclaration() throws CilParserException {
        parseSymbol("parser.error.expected_identifier");
        parseLevelRangeExpression(false);
    }

    private void parseLevelRangeExpression(boolean permitReference) throws CilParserException {
        if (permitReference && atReference(builder)) {
            parseReferenceExpression();
            return;
        }

        PsiBuilder.Marker marker = builder.mark();

        try {
            expect(builder, LPAREN, "parser.error.expected_lparen");
            parseSymbolAndListExpression(true, true, ExpressionParseHint.SECURITY_LEVEL);
            parseSymbolAndListExpression(true, true, ExpressionParseHint.SECURITY_LEVEL);
            expect(builder, RPAREN, "parser.error.expected_rparen");
            marker.done(LEVEL_RANGE_EXPR);
        } catch (CilParserException ex) {
            marker.drop();
            throw ex;
        }
    }

    private void parseMacroDeclaration() throws CilParserException {
        parseSymbol("parser.error.expected_identifier");
        expect(builder, LPAREN, "parser.error.expected_lparen");

        while (!builder.eof()) {
            if (builder.getTokenType() == LPAREN) {
                PsiBuilder.Marker argumentMarker = builder.mark();

                try {
                    expect(builder, LPAREN, "parser.error.expected_lparen");
                    expect(builder, IDENTIFIER, "parser.error.expected_macro_parameter_type");
                    expect(builder, IDENTIFIER, "parser.error.expected_macro_parameter_name");
                    expect(builder, RPAREN, "parser.error.expected_rparen");

                    argumentMarker.done(MACRO_ARGUMENT);
                } catch (CilParserException ex) {
                    rollbackAndRecover(argumentMarker, ex.getMessage());
                }

            } else if (builder.getTokenType() == RPAREN) {
                builder.advanceLexer();
                break;
            } else {
                throw new CilParserException(
                        CilBundle.message("parser.error.expected_macro_parameter",
                                builder.getTokenText()));
            }
        }

        parseStatements(TokenSet.create(RPAREN));
    }

    /**
     * Parse the list items of a {@link CilNamedListDeclaration}.
     */
    private void parseNamedListItems() throws CilParserException {
        expect(builder, LPAREN, "parser.error.expected_lparen");

        while (builder.getTokenType() == IDENTIFIER) {
            Marker itemMarker = builder.mark();

            try {
                parseSymbol("parser.error.expected_identifier");
                itemMarker.done(NAMED_LIST_ITEM_DECLARATION);
            } catch (CilParserException ex) {
                itemMarker.drop();
                throw ex;
            }
        }

        expect(builder, RPAREN, "parser.error.expected_rparen");
    }

    /**
     * Parse an expression referencing a symbol, optionally qualified preceding references
     * delimited by a {@code dot} ('.').
     */
    private void parseReferenceExpression() throws CilParserException {
        PsiBuilder.Marker expr = builder.mark();

        if (builder.getTokenType() == DOT) {
            builder.advanceLexer();
        }

        final int[] lastWsOffset = new int[1];
        lastWsOffset[0] = -1;

        builder.setWhitespaceSkippedCallback((type, start, end) -> {
            lastWsOffset[0] = end;
        });

        consume(builder, IDENTIFIER, REFERENCE_EXPR, "parser.error.expected_identifier");

        /*
         * Additionally check if the last whitespace detected was at the position before the qualifier
         * token, to avoid 2 whitespace delimited reference expressions incorrectly being parsed
         * as a single expression.
         */
        while (builder.getTokenType() == DOT && lastWsOffset[0] != builder.getCurrentOffset()) {
            builder.advanceLexer();

            if (builder.getTokenType() != IDENTIFIER) {
                break;
            }

            builder.advanceLexer();
            expr.done(REFERENCE_EXPR);
            expr = expr.precede();
        }

        builder.setWhitespaceSkippedCallback(null);
        expr.drop();
    }

    private void parseSidContext() throws CilParserException {
        parseReferenceExpression();
        parseContextExpression(true);
    }

    private void parseStatement() throws CilParserException {
        Marker marker = builder.mark();

        try {
            expect(builder, LPAREN, "parser.error.expected_lparen");
            expect(builder, IDENTIFIER, "parser.error.expected_identifier", false);

            String keyword = builder.getTokenText();
            if (keyword == null) {
                throw new RuntimeException("Unexpected error");
            }

            String normalizedKeyword = keyword.trim().toLowerCase();

            CilTopLevelElementType type = topLevelElementMap.get(normalizedKeyword)
                    .orElseThrow(() -> parserError("parser.error.invalid_statement_keyword",
                            normalizedKeyword));

            CilStatementParseHint parseHint = type.getParseHint();

            // Consume the keyword identifier and remap to a keyword token
            // for syntax highlighting
            builder.remapCurrentToken(CilTokenTypes.STATEMENT_KEYWORD);
            builder.advanceLexer();

            // @formatter:off
            switch (parseHint) {
                case CALL: parseCall(); break;
                case CLASS_MAPPING: parseClassMapping(); break;
                case CONSTRAIN: parseConstrain(); break;
                case CONTEXT_DECLARATION: parseContextDeclaration(); break;
                case NAMED_CONTAINER: parseContainer(type == CilTopLevelElementTypeMap.IN_STATEMENT); break;
                case FILE_CONTEXT: parseFileContext(); break;
                case LEVEL_DECLARATION: parseLevelDeclaration(); break;
                case LEVEL_RANGE_DECLARATION: parseLevelRangeDeclaration(); break;
                case MACRO_DECLARATION: parseMacroDeclaration(); break;
                case SID_CONTEXT: parseSidContext(); break;
                case NAME_DECLARATION: parseSymbolDeclaration(); break;
                case NAME_ASSOCIATION: parseSymbolAssociation(); break;
                case NAME_ORDERING: parseSymbolList(); break;
                case NAMED_LIST_DECLARATION: parseSymbolListDeclaration(); break;
                case NAMED_SET_MODIFIER: parseSymbolSetModifier(); break;
                case NAME_MODIFIER: parseSymbolStatement(); break;
                case TYPE_ENFORCEMENT_RULE: parseTypeEnforcementRule(); break;
                case TRANSITION_RULE: parseTransitionRule(); break;
                case TUNABLE_DECLARATION: parseTunableDeclaration(); break;
                case TUNABLE_IF: parseTunableIf(); break;
                default: throw new CilParserException(keyword +" is currently unsupported");
            }
            // @formatter:on

            if (builder.getTokenType() != RPAREN) {
                builder.error(message("parser.error.expected_rparen"));
            } else {
                builder.advanceLexer();
            }

            //@todo - get rid of this cast
            marker.done((IElementType) type);
        } catch (CilParserException ex) {
            marker.drop();
            throw ex;
        }
    }

    void parseStatements() {
        parseStatements(TokenSet.EMPTY);
    }

    private void parseStatements(TokenSet stopTokens) {
        while (!builder.eof() && !stopTokens.contains(builder.getTokenType())) {
            PsiBuilder.Marker checkpoint = builder.mark();

            try {
                parseStatement();
                checkpoint.drop();
            } catch (CilParserException ex) {
                rollbackAndRecover(checkpoint, ex.getMessage());
            }
        }
    }

    private void parseStringLiteral(String message) throws CilParserException {
        Marker marker = builder.mark();

        try {
            expect(builder, STRING, message);
            marker.done(STRING_EXPRESSION);
        } catch (CilParserException ex) {
            marker.drop();
            throw ex;
        }
    }

    /**
     * Parse a single identifier and remap it to a {@link CilTokenTypes#SYMBOL}.
     *
     * @param message The message to show as an error if an identifier wasn't found.
     */
    private void parseSymbol(String message) throws CilParserException {
        if (builder.getTokenType() != IDENTIFIER) {
            throw parserError(message);
        }

        builder.remapCurrentToken(CilTokenTypes.SYMBOL);
        builder.advanceLexer();
    }

    private void parseSymbolAndListExpression(boolean permitReference, boolean listOptional,
                                              ExpressionParseHint parseHint) throws CilParserException {
        if (permitReference && atReference(builder)) {
            parseReferenceExpression();
            return;
        }

        PsiBuilder.Marker marker = builder.mark();

        try {
            expect(builder, LPAREN, "parser.error.expected_lparen");
            parseReferenceExpression(); // class map or class identifier

            if (listOptional && builder.getTokenType() == RPAREN) {
                builder.advanceLexer();
                marker.done(SYMBOL_AND_LIST_EXPR);
                return;
            }

            PsiBuilder.Marker listMarker = builder.mark();

            try {
                expect(builder, LPAREN, "parser.error.expected_lparen");

                while (!builder.eof() && builder.getTokenType() != RPAREN) {
                    parseExpression(false, false, parseHint);
                }

                expect(builder, RPAREN, "parser.error.expected_rparen");
                listMarker.done(LIST_EXPR);
            } catch (CilParserException ex) {
                listMarker.drop();
                throw ex;
            }

            expect(builder, RPAREN, "parser.error.expected_rparen");
            marker.done(SYMBOL_AND_LIST_EXPR);
        } catch (CilParserException ex) {
            marker.drop();
            throw ex;
        }
    }

    /**
     * Parse an association statement, which contains references to 2 previously declared symbols.
     */
    private void parseSymbolAssociation() throws CilParserException {
        parseReferenceExpression(); // source
        parseReferenceExpression(); // target
    }

    /**
     * Parse a declaration of a single symbol name.
     */
    private void parseSymbolDeclaration() throws CilParserException {
        parseSymbol("parser.error.expected_identifier"); // symbol name
    }

    /**
     * Parse a list of symbols, represented as references within parenthesis:
     * <pre>
     *     (&lt;ref_0&gt; &lt;ref_1&gt; &lt;ref_N&gt;)
     * </pre>
     */
    private void parseSymbolList() throws CilParserException {
        expect(builder, LPAREN, "parser.error.expected_lparen");

        while (atReference(builder)) {
            parseReferenceExpression();
        }

        expect(builder, RPAREN, "parser.error.expected_rparen");
    }

    /**
     * Parse a declaration of a list, with each child as a separate list item declaration.
     */
    private void parseSymbolListDeclaration() throws CilParserException {
        parseSymbol("parser.error.expected_identifier");
        parseNamedListItems();
    }

    /**
     * Parse a {@code categoryset}, {@code typeattributeset}, {@code roleattributeset}, or {@code
     * userattributeset} statement.
     */
    private void parseSymbolSetModifier() throws CilParserException {
        parseReferenceExpression();
        parseExpression(false, false, ExpressionParseHint.ATTRIBUTE_OR_SET);
    }

    /**
     * Parse a statement with a single symbol reference as an argument.
     */
    private void parseSymbolStatement() throws CilParserException {
        parseReferenceExpression();
    }

    /**
     * Parse a {@code rangetransition}, {@code roletransition}, {@code typetransition}, {@code
     * typechange}, or {@code typemember} statement.  All transitions with the exception of {@code
     * rangetransition} accept an identifier as a new symbol, with the exception of {@code
     * rangetransition} which permits a level range expression.
     */
    private void parseTransitionRule() throws CilParserException {
        parseReferenceExpression(); // source
        parseReferenceExpression(); // target
        parseReferenceExpression(); // av class

        // if we're inside a macro the optional
        // string argument may be a reference expression

        if (builder.getTokenType() == STRING) {
            parseStringLiteral("Unexpected error");
        } else {
            parseReferenceExpression();
        }

        // if we didnt parse a string before and there's a reference to a string present,
        // parse the new symbol now
        if (atReference(builder)) {
            parseReferenceExpression();
        } else {

        }
    }

    /**
     * Parse a {@code tunable} boolean or compile-time option with a default value, in the form of:
     * <pre>
     * (tunable|boolean &lt;id&gt; true|false)
     * </pre>
     */
    private void parseTunableDeclaration() throws CilParserException {
        parseSymbol("parser.error.expected_identifier");
        expectBoolean(builder);
    }

    /**
     * Parse a conditional {@code tunableif} or {@code booleanif} statement, with optional branches.
     * <pre>
     * (booleanif|tunableif &lt;conditional_expression&gt;
     *      &lt;if_branch...&gt;)
     * </pre>
     */
    private void parseTunableIf() throws CilParserException {
        parseExpression(false, false, ExpressionParseHint.CONDITIONAL);

        // Conditional branches are optional,
        // and skipping them if absent is good
        // for error recovery anyway.
        while (builder.getTokenType() == LPAREN) {
            parseTunableIfBranch();
        }
    }

    /**
     * Parse a conditional branch, containing optional policy in the form of:
     * <pre>
     * (true|false
     *      &lt;statements...&gt;)
     * </pre>
     */
    private void parseTunableIfBranch() throws CilParserException {
        Marker marker = builder.mark();

        try {
            expect(builder, LPAREN, "parser.error.expected_lparen");
            expectBoolean(builder);
            parseStatements(TokenSet.create(RPAREN));
            expect(builder, RPAREN, "parser.error.expected_rparen");
            marker.done(TUNABLE_IF_BRANCH);
        } catch (CilParserException ex) {
            marker.drop();
            throw ex;
        }
    }

    /**
     * Parse a type enforcement rule ({@code auditallow}, {@code allow}, {@code dontaudit}, {@code
     * neverallow}) with a form of:
     * <pre>
     * (auditallow|allow|dontaudit|neverallow &lt;source&gt; &lt;target&gt;
     * &lt;access_vector_expr&gt;)
     * </pre>
     */
    private void parseTypeEnforcementRule() throws CilParserException {
        parseReferenceExpression(); // source
        parseReferenceExpression(); // target
        parseSymbolAndListExpression(true, false, ExpressionParseHint.ACCESS_VECTOR);
    }

    /**
     * Rollback to the position marked by the given {@code checkpoint} and find the closing
     * delimiter for the current expression.  Marking an error at the problematic code.
     *
     * @param checkpoint   The checkpoint marker.
     * @param errorMessage The message to associate with the problematic code.
     */
    private void rollbackAndRecover(Marker checkpoint, String errorMessage) {
        int errorOffset = builder.getCurrentOffset();
        checkpoint.rollbackTo();

        int delimiters = 0;
        do {
            IElementType type = builder.getTokenType();

            if (type == LPAREN) {
                delimiters++;
            } else if (type == RPAREN) {
                delimiters--;
            }

            if (errorOffset == builder.getCurrentOffset()) {
                Marker errorMarker = builder.mark();
                builder.advanceLexer();
                errorMarker.error(errorMessage);
            } else {
                builder.advanceLexer();
            }

        } while (!builder.eof() && delimiters > 0);
    }

    private enum ExpressionParseHint {
        CONDITIONAL("parser.error.expected_conditional_operator"),
        ACCESS_VECTOR("parser.error.expected_operator_or_accessvector_expr"),
        ATTRIBUTE_OR_SET("parser.error.expected_operator"),
        SECURITY_LEVEL("parser.error.expected_operator_or_seclevel_expr"),
        NONE("parser.error.expected_operator_or_named_list_expr"),
        CONSTRAIN("parser.error.expected_constrain_operator"),
        STRING_OR_NAME("parser.error.expected_string_or_error");

        private final String expressionIdentifierError;

        ExpressionParseHint(String expressionIdentifierError) {
            this.expressionIdentifierError = expressionIdentifierError;
        }
    }
}
