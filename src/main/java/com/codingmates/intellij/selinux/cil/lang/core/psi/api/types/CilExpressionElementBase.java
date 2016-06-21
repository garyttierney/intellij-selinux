package com.codingmates.intellij.selinux.cil.lang.core.psi.api.types;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilExpression;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilExpressionElementBase extends CilCompositeElementBase implements CilExpression {

    public CilExpressionElementBase(@NotNull ASTNode node) {
        super(node);
    }

}
