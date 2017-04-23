package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilInScope;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilStubbedElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilInScopeStub;
import com.google.common.base.Preconditions;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.CONTAINER_REFERENCE;

public class CilInScopeImpl extends CilStubbedElementBase<CilInScopeStub> implements CilInScope {

    public static final int NAMESPACE_REFERENCE_OFFSET = 0;

    public CilInScopeImpl(@NotNull ASTNode node) {
        super(node);
    }

    public CilInScopeImpl(CilInScopeStub stub, IStubElementType type) {
        super(stub, type);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        Preconditions.checkArgument(childOffset == NAMESPACE_REFERENCE_OFFSET, "Invalid child offset");

        return CONTAINER_REFERENCE;
    }

    @Override
    public CilReferenceExpression getNamespaceReference() {
        return CilPsiUtils.getChildAt(this, NAMESPACE_REFERENCE_OFFSET);
    }
}
