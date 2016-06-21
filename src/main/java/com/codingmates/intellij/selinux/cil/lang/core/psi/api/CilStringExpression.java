package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;

public interface CilStringExpression extends CilExpression, PsiLanguageInjectionHost {

    PsiElement getStringLiteral();


}
