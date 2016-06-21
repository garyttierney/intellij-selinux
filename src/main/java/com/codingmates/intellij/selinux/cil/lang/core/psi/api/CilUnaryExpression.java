package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;

public interface CilUnaryExpression extends CilExpression {

    CilOperator getOperator();

    CilCompositeElement getValue();
}
