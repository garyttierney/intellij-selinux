package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReference;

public interface CilReferenceExpression extends PsiReference, CilExpression {

    /**
     * Get a predicate which checks if a element type can resolve to his reference.
     *
     * @return The role of this reference.
     */
    CilReferenceRole getRole();

    PsiElement[] tryResolve(boolean incompleteCode);
}
