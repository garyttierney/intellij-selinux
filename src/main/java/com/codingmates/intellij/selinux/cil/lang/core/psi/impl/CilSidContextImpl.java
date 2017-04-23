package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilSidContext;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTypes.CONTEXT_DECLARATION;
import static com.codingmates.intellij.selinux.cil.lang.core.CilTypes.SID_DECLARATION;

public class CilSidContextImpl extends CilCompositeElementBase implements CilSidContext {

    public static final int CONTEXT_OFFSET = 1;
    public static final int SID_OFFSET = 0;

    public CilSidContextImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        switch (childOffset) {
            case SID_OFFSET:
                return CilReferenceRole.match(SID_DECLARATION);
            case CONTEXT_OFFSET:
                return CilReferenceRole.match(CONTEXT_DECLARATION);
            default:
                throw new IllegalArgumentException("Invalid child offset");
        }
    }

    @Override
    public CilExpression getContextExpression() {
        return CilPsiUtils.getChildAt(this, CONTEXT_OFFSET);
    }

    @Override
    public CilReferenceExpression getSidReference() {
        return CilPsiUtils.getChildAt(this, SID_OFFSET);
    }
}
