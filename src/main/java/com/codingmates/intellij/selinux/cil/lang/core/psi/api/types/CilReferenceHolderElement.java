package com.codingmates.intellij.selinux.cil.lang.core.psi.api.types;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.intellij.psi.tree.IElementType;

public interface CilReferenceHolderElement extends CilCompositeElement {

    CilReferenceRole getChildRole(IElementType childType, int childOffset);
}
