package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilCallArgumentList;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilCallStatement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.google.common.base.Preconditions;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.MACRO_REFERENCE;


public class CilCallStatementImpl extends CilCompositeElementBase implements CilCallStatement {

    private static final int ARGLIST_OFFSET = 1;
    private static final int MACRO_OFFSET = 0;

    public CilCallStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilCallArgumentList getArgumentList() {
        return CilPsiUtils.getChildAt(this, ARGLIST_OFFSET);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        Preconditions.checkArgument(childOffset == MACRO_OFFSET, "Invalid child offset");

        return MACRO_REFERENCE;
    }

    @Override
    public CilReferenceExpression getMacroReference() {
        return CilPsiUtils.getChildAt(this, MACRO_OFFSET);
    }
}
