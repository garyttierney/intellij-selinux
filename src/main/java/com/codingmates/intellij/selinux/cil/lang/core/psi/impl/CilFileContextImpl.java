package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilFileContext;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.CONTEXT_REFERENCE;

public class CilFileContextImpl extends CilCompositeElementBase implements CilFileContext {

    public static final int CONTEXT_OFFSET = 1;
    public static final int MATCHER_OFFSET = 0;
    public static final int OPTIONAL_PREFIX_OFFSET = 0;

    public CilFileContextImpl(@NotNull ASTNode node) {
        super(node);
    }


    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        Optional<CilExpression> prefix = getPrefix();

        int matcherOffset = adjustedOptionalOffset(prefix, OPTIONAL_PREFIX_OFFSET, MATCHER_OFFSET);
        int contextOffset = adjustedOptionalOffset(prefix, OPTIONAL_PREFIX_OFFSET, CONTEXT_OFFSET);

        if (childOffset == OPTIONAL_PREFIX_OFFSET || childOffset == matcherOffset) {
            return CilReferenceRole
                    .empty(); //@todo - string literal declarations (macro parameters)
        } else if (childOffset == contextOffset) {
            return CONTEXT_REFERENCE;
        } else {
            throw new IllegalArgumentException("Invalid child offset");
        }
    }

    @Override
    public CilExpression getContextExpression() {
        return CilPsiUtils.getChildAt(this,
                adjustedOptionalOffset(getPrefix(), OPTIONAL_PREFIX_OFFSET, CONTEXT_OFFSET));
    }

    @Override
    public CilExpression getMatcher() {
        return CilPsiUtils.getChildAt(this,
                adjustedOptionalOffset(getPrefix(), OPTIONAL_PREFIX_OFFSET, MATCHER_OFFSET));
    }

    @Override
    public Optional<CilExpression> getPrefix() {
        PsiElement[] children = getChildren();

        if (children.length == 3) {
            return Optional.of((CilExpression) children[OPTIONAL_PREFIX_OFFSET]);
        }

        return Optional.empty();
    }
}
