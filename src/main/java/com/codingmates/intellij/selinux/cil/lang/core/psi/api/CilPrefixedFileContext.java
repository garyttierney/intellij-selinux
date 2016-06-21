package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

public interface CilPrefixedFileContext extends CilReferenceHolderElement {

    CilExpression getContextExpression();

    CilExpression getMatcher();

    CilExpression getPrefix();
}
