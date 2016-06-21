package com.codingmates.intellij.selinux.cil.lang.core.parser;

import com.codingmates.intellij.selinux.cil.CilBundle;
import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * Utilities for parsing CIL source.
 *
 * @author gtierney
 */
public final class CilParserUtil {

    private CilParserUtil() {
    }

    /**
     * Check if the {@link PsiBuilder} is at the starting position of a reference (i.e., the current
     * token is a namespace separator or identifier).
     *
     * @param builder The {@link PsiBuilder}.
     * @return {@code true} iff the next node is a reference.
     */
    public static boolean atReference(PsiBuilder builder) {
        IElementType current = builder.getTokenType();

        return (current == CilTokenTypes.DOT && builder.lookAhead(1) == CilTokenTypes.IDENTIFIER) ||
                (current == CilTokenTypes.IDENTIFIER);
    }

    /**
     * Consume a single token of the {@code expected} type and remap it to {@code result}, if the expected token was not
     * found then raise a {@link CilParserException} with {@code message}.
     *
     * @see CilParserUtil#expect(PsiBuilder, TokenSet, String, boolean)
     */
    public static void consume(PsiBuilder builder, IElementType expected, IElementType result,
                               String message) throws CilParserException {
        PsiBuilder.Marker marker = builder.mark();
        expect(builder, expected, message);
        marker.done(result);
    }

    /**
     * Assert that the current token has a type of {@code type} and advance the {@code builder} forward.
     *
     * @see CilParserUtil#expect(PsiBuilder, IElementType, String, boolean)
     */
    public static void expect(PsiBuilder builder, IElementType type, String message)
            throws CilParserException {
        expect(builder, type, message, true);
    }

    /**
     * Assert that the current token has a type of {@code type} and optionally advance the {@code builder} forward.
     *
     * @see CilParserUtil#expect(PsiBuilder, TokenSet, String, boolean)
     */
    public static void expect(PsiBuilder builder, IElementType type, String message,
                              boolean advance) throws CilParserException {
        if (builder.getTokenType() != type) {
            throw parserError(message);
        }

        if (advance) {
            builder.advanceLexer();
        }
    }

    /**
     * Assert that the current token is one of {@code tokens} and optionally advance the {@code builder} if
     * {@code advance} is {@code true}.
     *
     * @param builder The parser builder.
     * @param tokens  The set of valid of tokens.
     * @param message The message to raise an error with if the assertion fails.
     * @param advance A flag indicating whether the builder should be advanced.
     * @throws CilParserException If the assertion fails.
     */
    public static void expect(PsiBuilder builder, TokenSet tokens, String message,
                              boolean advance) throws CilParserException {
        if (!tokens.contains(builder.getTokenType())) {
            throw parserError(message);
        }

        if (advance) {
            builder.advanceLexer();
        }
    }

    /**
     * Assert that the current token is a boolean literal and advance the builder.
     */
    public static void expectBoolean(PsiBuilder builder) throws CilParserException {
        expect(builder, TokenSet.create(CilTokenTypes.TRUE, CilTokenTypes.FALSE),
                "parser.error.expected_boolean", true);
    }

    /**
     * Return a new localized {@link CilParserException}.
     *
     * @param key    The message key.
     * @param params Arguments to be passed to the message.
     * @return A new {@link CilParserException} with a localized message.
     */
    public static CilParserException parserError(String key, Object... params) {
        return new CilParserException(CilBundle.message(key, params));
    }
}
