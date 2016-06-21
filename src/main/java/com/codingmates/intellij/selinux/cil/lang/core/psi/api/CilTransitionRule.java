package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilReferenceExpressionImpl;

import java.util.Optional;

public interface CilTransitionRule extends CilCompositeElement {

    CilReferenceExpressionImpl getResultReference();

    CilReferenceExpressionImpl getClassReference();

    CilReferenceExpressionImpl getSourceReference();

    CilReferenceExpressionImpl getTargetReference();

    Optional<CilExpression> getObjectName();
}
