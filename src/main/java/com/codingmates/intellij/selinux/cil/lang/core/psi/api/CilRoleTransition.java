package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilReferenceExpressionImpl;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;

public interface CilRoleTransition extends CilCompositeElement {

    int AVCLASS_OFFSET = 2;
    int NEW_ROLE_OFFSET = 3;
    int SOURCE_ROLE_OFFSET = 0;
    int TARGET_TYPE_OFFSET = 1;

    default CilReferenceExpressionImpl getAvClass() {
        return CilPsiUtils.getChildAt(this, AVCLASS_OFFSET);
    }

    default CilReferenceExpressionImpl getNewRole() {
        return CilPsiUtils.getChildAt(this, NEW_ROLE_OFFSET);
    }

    default CilReferenceExpressionImpl getSourceRole() {
        return CilPsiUtils.getChildAt(this, SOURCE_ROLE_OFFSET);
    }

    default CilReferenceExpressionImpl getTargetType() {
        return CilPsiUtils.getChildAt(this, TARGET_TYPE_OFFSET);
    }
}
