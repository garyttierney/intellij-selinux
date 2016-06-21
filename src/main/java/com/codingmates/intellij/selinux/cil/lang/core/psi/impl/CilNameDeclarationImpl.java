package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilSymbolDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationStubElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilSymbolDeclarationStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class CilNameDeclarationImpl extends CilDeclarationStubElementBase<CilSymbolDeclarationStub>
        implements CilSymbolDeclaration {

    public CilNameDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    public CilNameDeclarationImpl(CilSymbolDeclarationStub stub, IStubElementType type) {
        super(stub, type);
    }
}
