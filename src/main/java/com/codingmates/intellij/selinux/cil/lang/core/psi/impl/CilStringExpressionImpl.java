package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.CilStringLiteralEscaper;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilStringExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilExpressionElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;

public class CilStringExpressionImpl extends CilExpressionElementBase implements CilStringExpression {

    public CilStringExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement getStringLiteral() {
        return findChildByType(CilTokenTypes.STRING);
    }

    @Override
    public boolean isValidHost() {
        return true;
    }

    @Override
    public PsiLanguageInjectionHost updateText(@NotNull String text) {
        return null;
    }

    @NotNull
    @Override
    public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
        return new CilStringLiteralEscaper(this);
    }
}
