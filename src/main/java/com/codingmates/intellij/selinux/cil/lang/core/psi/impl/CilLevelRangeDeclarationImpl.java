package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilLevelRangeDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTypes.LEVEL_RANGE_EXPR;

public class CilLevelRangeDeclarationImpl extends CilDeclarationElementBase implements
        CilLevelRangeDeclaration {

    public CilLevelRangeDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilLevelRangeExpressionImpl getExpression() {
        return findNotNullChildByType(LEVEL_RANGE_EXPR);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        return null;
    }
}
