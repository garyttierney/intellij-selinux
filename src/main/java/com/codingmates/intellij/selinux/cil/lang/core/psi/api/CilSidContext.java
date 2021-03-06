package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

public interface CilSidContext extends CilReferenceHolderElement {

    CilExpression getContextExpression();

    CilReferenceExpression getSidReference();
}
