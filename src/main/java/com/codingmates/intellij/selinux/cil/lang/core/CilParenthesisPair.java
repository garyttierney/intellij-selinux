package com.codingmates.intellij.selinux.cil.lang.core;

import com.intellij.lang.BracePair;

public class CilParenthesisPair extends BracePair {

    public CilParenthesisPair() {
        super(CilTokenTypes.LPAREN, CilTokenTypes.RPAREN, true);
    }
}
