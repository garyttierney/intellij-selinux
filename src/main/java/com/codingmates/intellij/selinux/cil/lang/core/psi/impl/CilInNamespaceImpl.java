package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilInNamespace;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.google.common.base.Preconditions;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.CONTAINER_DECLARATIONS;

public class CilInNamespaceImpl extends CilCompositeElementBase implements CilInNamespace {

    public static final int NAMESPACE_REFERENCE_OFFSET = 0;

    public CilInNamespaceImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        Preconditions
                .checkArgument(childOffset == NAMESPACE_REFERENCE_OFFSET, "Invalid child offset");

        return CONTAINER_DECLARATIONS;
    }

    @Override
    public CilReferenceExpression getNamespaceReference() {
        return CilPsiUtils.getChildAt(this, NAMESPACE_REFERENCE_OFFSET);
    }
}
