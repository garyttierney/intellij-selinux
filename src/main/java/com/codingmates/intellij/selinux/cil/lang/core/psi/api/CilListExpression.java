package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import java.util.List;

public interface CilListExpression extends CilExpression {

    List<CilExpression> getExpressions();
}
