package com.codingmates.intellij.selinux.cil.lang.core.stubs.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;

public abstract class CilNamedElementStub<T extends CilDeclarationElement> extends
        NamedStubBase<T> {

    private final StringRef nameRef;

    protected CilNamedElementStub(StubElement parent, @NotNull IStubElementType elementType,
                                  StringRef name) {
        super(parent, elementType, name);
        nameRef = name;
    }

    protected CilNamedElementStub(StubElement parent, @NotNull IStubElementType elementType,
                                  String name) {
        this(parent, elementType, StringRef.fromString(name));
    }

    public String getName() {
        return nameRef.getString();
    }
}
