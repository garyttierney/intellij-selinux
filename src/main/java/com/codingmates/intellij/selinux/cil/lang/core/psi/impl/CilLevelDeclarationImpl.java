package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilLevelDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTypes.SYMBOL_AND_LIST_EXPR;

public class CilLevelDeclarationImpl extends CilDeclarationElementBase implements
        CilLevelDeclaration {

    public CilLevelDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    public CilNamedListExpression getExpression() {
        return findChildByType(SYMBOL_AND_LIST_EXPR);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        return null;
    }
}
