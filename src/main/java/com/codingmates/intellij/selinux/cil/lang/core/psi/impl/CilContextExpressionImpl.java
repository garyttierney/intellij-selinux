package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilContextExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.TYPE_OR_ALIAS_REFERENCE;

public class CilContextExpressionImpl extends CilCompositeElementBase implements
        CilContextExpression {

    public static final int LEVEL_RANGE_OFFSET = 3;
    public static final int ROLE_OFFSET = 1;
    public static final int TYPE_OFFSET = 2;
    public static final int USER_OFFSET = 0;

    public CilContextExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        switch (childOffset) {
            case USER_OFFSET:
                return CilReferenceRole.match(CilTypes.USER_DECLARATION);
            case ROLE_OFFSET:
                return CilReferenceRole.match(CilTypes.ROLE_DECLARATION);
            case TYPE_OFFSET:
                return TYPE_OR_ALIAS_REFERENCE;
            case LEVEL_RANGE_OFFSET:
                return CilReferenceRole.match(CilTypes.LEVELRANGE_DECLARATION);
            default:
                throw new IllegalArgumentException("Invalid child offset");
        }
    }

    @Override
    public CilCompositeElement getLevelRangeExpression() {
        return CilPsiUtils.getChildAt(this, LEVEL_RANGE_OFFSET);
    }

    @Override
    public CilReferenceExpression getRoleReference() {
        return CilPsiUtils.getChildAt(this, ROLE_OFFSET);

    }

    @Override
    public CilReferenceExpression getTypeReference() {
        return CilPsiUtils.getChildAt(this, TYPE_OFFSET);

    }

    @Override
    public CilReferenceExpression getUserReference() {
        return CilPsiUtils.getChildAt(this, USER_OFFSET);
    }
}
