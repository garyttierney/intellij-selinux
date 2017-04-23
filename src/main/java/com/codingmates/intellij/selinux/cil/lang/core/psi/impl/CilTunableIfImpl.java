package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilTunableIf;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.google.common.base.Preconditions;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class CilTunableIfImpl extends CilCompositeElementBase implements CilTunableIf {

    private static final int BOOLEAN_EXPR_OFFSET = 0;

    public CilTunableIfImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        Preconditions.checkArgument(childOffset == BOOLEAN_EXPR_OFFSET, "Invalid child offset");

        IElementType thisType = getElementType();

        if (thisType == CilTypes.TUNABLEIF_STATEMENT) {
            return CilReferenceRole.match(CilTypes.TUNABLE_DECLARATION);
        } else if (thisType == CilTypes.BOOLEANIF_STATEMENT) {
            return CilReferenceRole.match(CilTypes.BOOLEAN_DECLARATION);
        } else {
            throw new IllegalStateException("Invalid element type for tunableif");
        }
    }
}
