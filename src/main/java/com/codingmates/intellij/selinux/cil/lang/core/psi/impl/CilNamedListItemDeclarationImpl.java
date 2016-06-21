package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListItemDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationStubElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilNamedListItemDeclarationStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class CilNamedListItemDeclarationImpl extends
        CilDeclarationStubElementBase<CilNamedListItemDeclarationStub> implements
        CilNamedListItemDeclaration {

    public CilNamedListItemDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    public CilNamedListItemDeclarationImpl(CilNamedListItemDeclarationStub stub,
                                           IStubElementType type) {
        super(stub, type);
    }

    @Override
    public PsiElement getNameIdentifier() {
        return findNotNullChildByType(CilTokenTypes.SYMBOL);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        return null;
    }
}
