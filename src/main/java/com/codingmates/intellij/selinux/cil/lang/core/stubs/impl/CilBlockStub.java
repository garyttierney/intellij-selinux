package com.codingmates.intellij.selinux.cil.lang.core.stubs.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockDeclaration;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;

public class CilBlockStub extends CilNamedElementStub<CilBlockDeclaration> {

    public CilBlockStub(StubElement parent, @NotNull IStubElementType elementType, StringRef name) {
        super(parent, elementType, name);
    }

    public CilBlockStub(StubElement parent, @NotNull IStubElementType elementType, String name) {
        super(parent, elementType, name);
    }

}
