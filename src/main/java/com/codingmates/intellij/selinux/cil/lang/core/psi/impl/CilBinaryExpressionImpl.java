package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBinaryExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilOperator;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilExpressionElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilBinaryExpressionImpl extends CilExpressionElementBase implements
        CilBinaryExpression {

    public CilBinaryExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilExpression getLeft() {
        return null;
    }

    @Override
    public CilOperator getOperator() {
        return CilOperator.from(findChildByType(CilTokenTypes.IDENTIFIER).getText());
    }

    @Override
    public CilExpression getRight() {
        return null;
    }
}
