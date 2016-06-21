package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilListExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilExpressionElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CilListExpressionImpl extends CilExpressionElementBase implements CilListExpression {

    public CilListExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public List<CilExpression> getExpressions() {
        return findChildrenByType(
                TokenSet.create(CilTypes.REFERENCE_EXPR, CilTypes.BINARY_EXPR, CilTypes.UNARY_EXPR));
    }
}
