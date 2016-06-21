package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilOperator;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilUnaryExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilExpressionElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class CilUnaryExpressionImpl extends CilExpressionElementBase implements CilUnaryExpression {

    public CilUnaryExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilOperator getOperator() {
        return CilOperator.from(findNotNullChildByType(CilTokenTypes.IDENTIFIER).getText());
    }

    @Override
    public CilCompositeElement getValue() {
        return findChildByType(TokenSet.create(CilTypes.REFERENCE_EXPR, CilTypes.BINARY_EXPR,
                CilTypes.UNARY_EXPR, CilTypes.LIST_EXPR));
    }
}
