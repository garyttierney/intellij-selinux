package com.codingmates.intellij.selinux.cil.lang.core.lexer;

import com.intellij.lexer.FlexAdapter;

public class CilLexerAdapter extends FlexAdapter {

    public CilLexerAdapter() {
        super(new CilLexer());
    }
}
