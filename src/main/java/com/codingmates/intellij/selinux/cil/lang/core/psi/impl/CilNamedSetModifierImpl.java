package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedSetModifier;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

public class CilNamedSetModifierImpl extends CilCompositeElementBase implements
        CilNamedSetModifier {

    public static final int EXPRESSION_OFFSET = 1;
    public static final int SET_REFERENCE_OFFSET = 0;

    public CilNamedSetModifierImpl(ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        IElementType setDeclarationType = getSetElementType();

        switch (childOffset) {
            case SET_REFERENCE_OFFSET:
                return CilReferenceRole.match(setDeclarationType);
            case EXPRESSION_OFFSET:
                return CilReferenceRoles.declarationsLike(setDeclarationType);
            default:
                throw new IllegalArgumentException("Invalid child offset");
        }
    }

    @Override
    public CilCompositeElement getModifierExpression() {
        return CilPsiUtils.getChildAt(this, EXPRESSION_OFFSET);
    }

    public IElementType getSetElementType() {
        IElementType thisType = getNode().getElementType();

        if (thisType == CilTypes.TYPEATTRIBUTESET_STATEMENT) {
            return CilTypes.TYPEATTRIBUTE_DECLARATION;
        } else if (thisType == CilTypes.ROLEATTRIBUTESET_STATEMENT) {
            return CilTypes.ROLEATTRIBUTE_DECLARATION;
        } else if (thisType == CilTypes.CLASSPERMISSIONSET_STATEMENT) {
            return CilTypes.CLASSPERMISSION_DECLARATION;
        } else {
            throw new IllegalStateException("Invalid named set modifier type");
        }
    }

    @Override
    public CilReferenceExpression getSetReference() {
        return CilPsiUtils.getChildAt(this, SET_REFERENCE_OFFSET);
    }
}
