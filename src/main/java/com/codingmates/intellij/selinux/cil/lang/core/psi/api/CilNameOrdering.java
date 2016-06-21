package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

import java.util.List;

public interface CilNameOrdering extends CilCompositeElement, CilReferenceHolderElement {

    List<CilReferenceExpression> getNameReferences();
}
