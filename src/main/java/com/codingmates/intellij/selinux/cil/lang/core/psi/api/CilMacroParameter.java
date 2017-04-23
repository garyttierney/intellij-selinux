package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface CilMacroParameter extends CilDeclarationElement {
    IElementType getParameterType();

    PsiElement getTypeElement();
}
