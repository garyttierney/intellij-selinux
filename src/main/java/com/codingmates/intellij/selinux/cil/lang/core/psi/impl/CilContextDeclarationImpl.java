package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilContextDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilContextExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class CilContextDeclarationImpl extends CilDeclarationElementBase implements
        CilContextDeclaration {

    public CilContextDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilContextExpression getValue() {
        return findNotNullChildByType(CilTypes.CONTEXT_EXPR);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        return null;
    }
}
