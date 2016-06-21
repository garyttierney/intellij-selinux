package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;

public interface CilExpression extends CilCompositeElement {

    default <T> T accept(CilExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
