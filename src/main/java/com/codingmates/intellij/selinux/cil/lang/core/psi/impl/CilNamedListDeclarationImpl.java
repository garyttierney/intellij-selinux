package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListItemDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationStubElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilResolveUtil;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilNamedListDeclarationStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CilNamedListDeclarationImpl extends
        CilDeclarationStubElementBase<CilNamedListDeclarationStub> implements CilNamedListDeclaration {

    public CilNamedListDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    public CilNamedListDeclarationImpl(CilNamedListDeclarationStub stub, IStubElementType type) {
        super(stub, type);
    }

    @Override
    public List<CilNamedListItemDeclaration> getItems() {
        return findChildrenByType(CilTypes.NAMED_LIST_ITEM_DECLARATION);
    }
}
