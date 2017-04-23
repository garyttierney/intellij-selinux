package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilMacroDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilMacroParameter;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationStubElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilMacroStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CilMacroDeclarationImpl extends CilDeclarationStubElementBase<CilMacroStub> implements
        CilMacroDeclaration {

    public CilMacroDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    public CilMacroDeclarationImpl(@NotNull CilMacroStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public List<CilMacroParameterImpl> getArgumentList() {
        return findChildrenByType(CilTypes.MACRO_PARAMETER_DECL);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return findChildByType(CilTokenTypes.SYMBOL);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        return this;
    }

    @Override
    public List<CilMacroParameter> getParameters() {
        return findChildrenByType(CilTypes.MACRO_PARAMETER_DECL);
    }
}
