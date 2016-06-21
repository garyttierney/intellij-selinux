package com.codingmates.intellij.selinux.cil.lang.core.stubs.impl;


import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListDeclaration;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;

public class CilNamedListDeclarationStub extends CilNamedElementStub<CilNamedListDeclaration> {

    public CilNamedListDeclarationStub(StubElement parent, @NotNull IStubElementType elementType,
                                       StringRef name) {
        super(parent, elementType, name);
    }

    public CilNamedListDeclarationStub(StubElement parent, @NotNull IStubElementType elementType,
                                       String name) {
        super(parent, elementType, name);
    }

}
