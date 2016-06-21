package com.codingmates.intellij.selinux.cil.lang.core;

public final class CilTokenTypes {

    /**
     * A dummy token used by the syntax highlighting lexer.
     */
    public static final CilTokenType STATEMENT_KEYWORD = new CilTokenType("statement_flavor");
    public static final CilTokenType OPERATOR = new CilTokenType("operator");
    public static final CilTokenType LPAREN = new CilTokenType("(");
    public static final CilTokenType RPAREN = new CilTokenType(")");
    public static final CilTokenType TRUE = new CilTokenType("true");
    public static final CilTokenType FALSE = new CilTokenType("false");
    public static final CilTokenType STRING = new CilTokenType("string");
    public static final CilTokenType IDENTIFIER = new CilTokenType("identifier");
    public static final CilTokenType SYMBOL = new CilTokenType("symbol");
    public static final CilTokenType SEMICOLON = new CilTokenType(";");
    public static final CilTokenType DOT = new CilTokenType(".");

    private CilTokenTypes() {
    }

}
