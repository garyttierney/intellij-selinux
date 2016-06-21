package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilTransitionRule;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap.ROLETRANSITION_STATEMENT;
import static com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap.ROLE_DECLARATION;
import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.*;

public class CilTransitionRuleImpl extends CilCompositeElementBase implements CilTransitionRule,
        CilReferenceHolderElement {

    public static final int OBJECT_CLASS_OFFSET = 2;
    public static final int OPTIONAL_OBJECT_NAME_OFFSET = 3;
    public static final int RESULT_OFFSET = 3;
    public static final int SOURCE_OFFSET = 0;
    public static final int TARGET_OFFSET = 1;


    public CilTransitionRuleImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        boolean isRoleTransition = getElementType() == ROLETRANSITION_STATEMENT;

        Optional<CilExpression> objectName = getObjectName();
        int adjustedResultOffset = adjustedOptionalOffset(objectName, OPTIONAL_OBJECT_NAME_OFFSET,
                RESULT_OFFSET);

        switch (childOffset) {
            case SOURCE_OFFSET:
            case TARGET_OFFSET:
                return isRoleTransition ? ROLE_SET_DECLARATIONS : TYPE_SET_DECLARATIONS;
            case OBJECT_CLASS_OFFSET:
                return ACCESS_VECTOR_DECLARATIONS;
            case OPTIONAL_OBJECT_NAME_OFFSET:
                if (objectName.isPresent()) {
                    return CilReferenceRole.empty();
                }
            default:
                if (childOffset == adjustedResultOffset) {
                    return isRoleTransition ? CilReferenceRole.match(ROLE_DECLARATION)
                            : TYPE_DECLARATIONS;
                }

                throw new IllegalArgumentException("Invalid child offset");
        }
    }

    @Override
    public CilReferenceExpressionImpl getClassReference() {
        return CilPsiUtils.getChildAt(this, OBJECT_CLASS_OFFSET);
    }

    @Override
    public Optional<CilExpression> getObjectName() {
        PsiElement[] children = getChildren();

        if (children.length == 5) {
            return Optional.of((CilExpression) children[OPTIONAL_OBJECT_NAME_OFFSET]);
        }

        return Optional.empty();
    }

    @Override
    public CilReferenceExpressionImpl getResultReference() {
        return CilPsiUtils.getChildAt(this,
                adjustedOptionalOffset(getObjectName(), OPTIONAL_OBJECT_NAME_OFFSET, RESULT_OFFSET));
    }

    @Override
    public CilReferenceExpressionImpl getSourceReference() {
        return CilPsiUtils.getChildAt(this, SOURCE_OFFSET);
    }

    @Override
    public CilReferenceExpressionImpl getTargetReference() {
        return CilPsiUtils.getChildAt(this, TARGET_OFFSET);
    }

}
