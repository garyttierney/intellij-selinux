package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.google.common.base.Preconditions;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap.*;
import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.BLOCK_DECLARATION_REF;
import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.TYPE_DECLARATIONS;

public class CilNameModifierImpl extends CilCompositeElementBase implements
        com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNameModifier {

    private static final int NAME_OFFSET = 0;

    public CilNameModifierImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        Preconditions.checkArgument(childOffset == NAME_OFFSET, "Invalid child offset");

        IElementType type = getElementType();

        if (type == BLOCKABSTRACT_STATEMENT || type == BLOCKINHERIT_STATEMENT) {
            return BLOCK_DECLARATION_REF;
        } else if (type == TYPEPERMISSIVE_STATEMENT) {
            return TYPE_DECLARATIONS;
        } else {
            throw new IllegalStateException("Invalid modifier type");
        }
    }

    @Override
    @NotNull
    public CilReferenceExpression getNameReference() {
        return findNotNullChildByType(CilTypes.REFERENCE_EXPR);
    }
}
