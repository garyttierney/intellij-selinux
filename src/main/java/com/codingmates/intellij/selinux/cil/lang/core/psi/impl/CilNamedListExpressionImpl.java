package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilListExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilExpressionElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilNamedListExpressionImpl extends CilExpressionElementBase implements CilNamedListExpression {

    public CilNamedListExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilListExpression getExpressions() {
        return null;
    }

    @Override
    public CilReferenceExpression getListReference() {
        return findNotNullChildByType(CilTypes.REFERENCE_EXPR);
    }
}
