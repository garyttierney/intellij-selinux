package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilInScopeStub;
import com.intellij.psi.StubBasedPsiElement;

public interface CilInScope extends CilCompositeElement, CilReferenceHolderElement,
        StubBasedPsiElement<CilInScopeStub> {

    CilReferenceExpression getNamespaceReference();
}
