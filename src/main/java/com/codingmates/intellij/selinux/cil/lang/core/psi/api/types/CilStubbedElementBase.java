package com.codingmates.intellij.selinux.cil.lang.core.psi.api.types;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import org.jetbrains.annotations.NotNull;

public class CilStubbedElementBase<T extends StubElement<?>> extends
        StubBasedPsiElementBase<T> implements CilCompositeElement, StubBasedPsiElement<T> {

    public CilStubbedElementBase(@NotNull T stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public CilStubbedElementBase(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getElementType().toString();
    }
}
