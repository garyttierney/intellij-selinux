package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import org.jetbrains.annotations.NotNull;

public interface CilNetworkInterfaceContext extends CilCompositeElement {

    @NotNull
    CilCompositeElement getContext();
}
