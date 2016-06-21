package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

public interface CilNamedListExpression extends CilExpression {

    CilListExpression getExpressions();

    CilReferenceExpression getListReference();
}
