package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;
import org.jetbrains.annotations.NotNull;

public interface CilNameModifier extends CilCompositeElement, CilReferenceHolderElement {

    @NotNull
    CilReferenceExpression getNameReference();
}
