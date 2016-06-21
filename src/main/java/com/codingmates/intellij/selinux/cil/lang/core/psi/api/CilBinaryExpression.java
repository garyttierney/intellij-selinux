package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

public interface CilBinaryExpression extends CilExpression {

    CilExpression getLeft();

    CilOperator getOperator();

    CilExpression getRight();
}
