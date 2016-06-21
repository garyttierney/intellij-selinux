package com.codingmates.intellij.selinux.cil.lang.core.stubs.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilSymbolDeclaration;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;

public class CilSymbolDeclarationStub extends CilNamedElementStub<CilSymbolDeclaration> {

    public CilSymbolDeclarationStub(StubElement parent, @NotNull IStubElementType elementType,
                                    StringRef name) {
        super(parent, elementType, name);
    }

    public CilSymbolDeclarationStub(StubElement parent, @NotNull IStubElementType elementType,
                                    String name) {
        super(parent, elementType, name);
    }
}
