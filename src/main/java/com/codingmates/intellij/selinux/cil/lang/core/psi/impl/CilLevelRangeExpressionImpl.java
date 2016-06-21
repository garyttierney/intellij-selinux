package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilLevelRangeExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilExpressionElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilLevelRangeExpressionImpl extends CilExpressionElementBase implements
        CilLevelRangeExpression {

    public CilLevelRangeExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }
}
