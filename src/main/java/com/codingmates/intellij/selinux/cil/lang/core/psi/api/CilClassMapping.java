package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

public interface CilClassMapping extends CilCompositeElement, CilReferenceHolderElement {

    CilCompositeElement getAccessVectorExpression();

    CilReferenceExpression getClassMapReference();

    CilReferenceExpression getClassMapChildReference();
}
