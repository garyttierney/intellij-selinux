package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilCallArgumentList;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.TYPE_SET_DECLARATIONS;

public class CilCallArgumentListImpl extends CilCompositeElementBase implements
        CilCallArgumentList {

    public CilCallArgumentListImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public List<CilExpression> getArguments() {
        return Arrays.asList(findChildrenByClass(CilExpression.class));
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        //@todo - resolve from macro arguments
        return TYPE_SET_DECLARATIONS;
    }
}
