package com.codingmates.intellij.selinux.cil.lang.core;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

public interface CilElementFactory {

    PsiElement createElement(ASTNode node);
}
