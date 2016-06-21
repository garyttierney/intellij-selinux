package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.intellij.psi.PsiPolyVariantReference;

public interface CilReferenceExpression extends PsiPolyVariantReference, CilExpression {

    /**
     * Get a predicate which checks if a element type can resolve to his reference.
     *
     * @return The role of this reference.
     */
    CilReferenceRole getRole();

}
