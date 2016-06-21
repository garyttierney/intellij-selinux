package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

import java.util.Optional;

public interface CilFileContext extends CilReferenceHolderElement {

    CilExpression getContextExpression();

    CilExpression getMatcher();

    Optional<CilExpression> getPrefix();
}
