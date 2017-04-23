package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilContainerElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilMacroStub;
import com.intellij.psi.StubBasedPsiElement;

import java.util.List;

public interface CilMacroDeclaration extends CilContainerElement, CilDeclarationElement,
        StubBasedPsiElement<CilMacroStub> {

    List<CilMacroParameter> getParameters();
}
