package com.codingmates.intellij.selinux.cil.lang.core.syntax;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.lexer.CilLexerAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class CilSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey COMMENT =
            createTextAttributesKey("CIL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("CIL_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey NUMBER = createTextAttributesKey("CIL_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER);
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey STRING_LITERAL = createTextAttributesKey(
            "CIL_STRING_LITERAL",
            DefaultLanguageHighlighterColors.STRING);
    private static final TextAttributesKey[] STRING_LITERAL_KEYS = new TextAttributesKey[]{
            STRING_LITERAL};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new CilLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(CilTokenTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        } else if (tokenType.equals(CilTokenTypes.STRING)) {
            return STRING_LITERAL_KEYS;
        } else if (tokenType.equals(CilTokenTypes.SEMICOLON)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(CilTokenTypes.TRUE) || tokenType.equals(CilTokenTypes.FALSE)) {
            return KEYWORD_KEYS;
        }

        return EMPTY_KEYS;
    }
}
