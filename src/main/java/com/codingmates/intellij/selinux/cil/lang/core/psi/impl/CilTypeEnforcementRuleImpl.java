package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilTypeEnforcementRule;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.ACCESS_VECTOR_DECLARATIONS;
import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.TYPE_SET_DECLARATIONS;

public class CilTypeEnforcementRuleImpl extends CilCompositeElementBase implements
        CilTypeEnforcementRule {

    public static final int ACCESS_VECTOR_EXPR_OFFSET = 2;
    public static final int SOURCE_REFERENCE_OFFSET = 0;
    public static final int TARGET_REFERENCE_OFFSET = 1;

    public CilTypeEnforcementRuleImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilCompositeElement getAccessVectorExpression() {
        return CilPsiUtils.getChildAt(this, ACCESS_VECTOR_EXPR_OFFSET);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        switch (childOffset) {
            case SOURCE_REFERENCE_OFFSET:
            case TARGET_REFERENCE_OFFSET:
                return TYPE_SET_DECLARATIONS;
            case ACCESS_VECTOR_EXPR_OFFSET:
                return ACCESS_VECTOR_DECLARATIONS;
            default:
                throw new IllegalArgumentException("Invalid child offset " + childOffset);
        }
    }

    @Override
    public CilReferenceExpression getSourceReference() {
        return CilPsiUtils.getChildAt(this, SOURCE_REFERENCE_OFFSET);
    }

    @Override
    public CilReferenceExpression getTargetReference() {
        return CilPsiUtils.getChildAt(this, TARGET_REFERENCE_OFFSET);
    }

    public CilTypeEnforcementRuleType getType() {
        PsiElement typeIdentifier = findChildByType(CilTokenTypes.IDENTIFIER);
        if (typeIdentifier == null) {
            throw new IllegalStateException("No avrule type identifier found");
        }

        String text = typeIdentifier.getText();
        return CilTypeEnforcementRuleType.valueOf(text.toUpperCase());
    }
}
