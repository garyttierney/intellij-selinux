package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.CilNameAssociationElementType;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilSymbolAssociation;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.google.common.base.Preconditions;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class CilNameAssociationImpl extends CilCompositeElementBase implements
        CilSymbolAssociation {

    private static final String INVALID_OFFSET_ERROR = "Invalid child offset";
    private static final int SOURCE_OFFSET = 0;
    private static final int TARGET_OFFSET = 1;

    public CilNameAssociationImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        Preconditions.checkArgument(childOffset == SOURCE_OFFSET || childOffset == TARGET_OFFSET,
                INVALID_OFFSET_ERROR);

        CilNameAssociationElementType thisType = (CilNameAssociationElementType) getElementType();
        return childOffset == SOURCE_OFFSET ? thisType.getSourceRole() : thisType.getTargetRole();
    }

    public CilReferenceExpression getSourceSymbol() {
        return CilPsiUtils.getChildAt(this, SOURCE_OFFSET);
    }

    @Override
    public CilReferenceExpression getTargetSymbol() {
        return CilPsiUtils.getChildAt(this, TARGET_OFFSET);
    }
}
