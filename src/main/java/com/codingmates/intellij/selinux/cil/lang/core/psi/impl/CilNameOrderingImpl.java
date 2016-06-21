package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.CilNameOrderingElementType;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNameOrdering;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.google.common.base.Preconditions;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CilNameOrderingImpl extends CilCompositeElementBase implements CilNameOrdering {

    public CilNameOrderingImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        List<CilReferenceExpression> references = getNameReferences();
        Preconditions.checkPositionIndex(childOffset, references.size());

        CilNameOrderingElementType thisType = (CilNameOrderingElementType) getElementType();
        return thisType.getNameRole();
    }

    @Override
    public List<CilReferenceExpression> getNameReferences() {
        return findChildrenByType(CilTypes.REFERENCE_EXPR);
    }
}
