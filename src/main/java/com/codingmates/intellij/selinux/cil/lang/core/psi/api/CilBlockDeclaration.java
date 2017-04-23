package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilContainerElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilBlockDeclarationStub;
import com.intellij.psi.StubBasedPsiElement;

public interface CilBlockDeclaration extends CilDeclarationElement, CilContainerElement,
        StubBasedPsiElement<CilBlockDeclarationStub> {

    boolean isAbstract();
}
