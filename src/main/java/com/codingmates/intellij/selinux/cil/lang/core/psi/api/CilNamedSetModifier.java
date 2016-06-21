package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

/**
 * A statement which adds or removes elements to a named set.
 *
 * @author gtierney
 */
public interface CilNamedSetModifier extends CilCompositeElement, CilReferenceHolderElement {

    /**
     * Get the {@link com.intellij.psi.PsiElement} which represents the expression that modifies the
     * named set. Can only be an {@link CilExpression}.
     *
     * @return The expression that modifies the named attribute.
     */
    CilCompositeElement getModifierExpression();

    /**
     * Get the reference to the symbol that is being modified.
     *
     * @return A reference to the symbol being modified.
     */
    CilReferenceExpression getSetReference();

}
