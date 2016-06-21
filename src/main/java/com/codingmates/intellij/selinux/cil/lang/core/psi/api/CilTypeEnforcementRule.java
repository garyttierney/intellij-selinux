package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilTypeEnforcementRuleType;

/**
 * A type enforcement rule defined in the policy.
 *
 * @author gtierney
 */
public interface CilTypeEnforcementRule extends CilCompositeElement, CilReferenceHolderElement {

    /**
     * Get the name of the source type in this {@code CilTypeEnforcementRule}.
     *
     * @return The name of the source type.
     */
    CilReferenceExpression getSourceReference();

    /**
     * Get the name of the target type in this {@code CilTypeEnforcementRule}.
     *
     * @return The name of the target type.
     */
    CilReferenceExpression getTargetReference();

    /**
     * Get the type (i.e., the statement flavor) of this rule.
     *
     * @return The type of this rule.
     */
    CilTypeEnforcementRuleType getType();

    CilCompositeElement getAccessVectorExpression();
}
