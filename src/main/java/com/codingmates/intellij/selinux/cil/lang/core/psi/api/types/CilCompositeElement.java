package com.codingmates.intellij.selinux.cil.lang.core.psi.api.types;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface CilCompositeElement extends PsiElement {
    IElementType getElementType();
}
