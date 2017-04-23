package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNameModifier;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationStubElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilResolveUtil;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilBlockDeclarationStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.annotations.NotNull;

public class CilBlockDeclarationImpl extends CilDeclarationStubElementBase<CilBlockDeclarationStub> implements CilBlockDeclaration {

    public CilBlockDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    public CilBlockDeclarationImpl(CilBlockDeclarationStub stub, IStubElementType type) {
        super(stub, type);
    }

    @NotNull
    @Override
    public PsiElement getNameIdentifier() {
        return findNotNullChildByType(CilTokenTypes.SYMBOL);
    }

    public boolean isAbstract() {
        CilNameModifier blockAbstract = findChildByType(CilTypes.BLOCKABSTRACT_STATEMENT);

        if (blockAbstract == null) {
            return false;
        }

        return CilResolveUtil.resolve(blockAbstract.getNameReference())
                .map(this::isEquivalentTo)
                .orElse(false);
    }
}
