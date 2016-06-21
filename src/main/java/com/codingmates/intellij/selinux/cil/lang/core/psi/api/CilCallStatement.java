package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

public interface CilCallStatement extends CilCompositeElement, CilReferenceHolderElement {

    CilCallArgumentList getArgumentList();

    CilReferenceExpression getMacroReference();
}
