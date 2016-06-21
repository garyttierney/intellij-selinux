package com.codingmates.intellij.selinux.cil.lang.core.stubs;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;

public abstract class CilNamedStub<T extends CilDeclarationElement> extends NamedStubBase<T> {

    protected CilNamedStub(StubElement parent, @NotNull IStubElementType elementType,
                           StringRef name) {
        super(parent, elementType, name);
    }

    protected CilNamedStub(StubElement parent, @NotNull IStubElementType elementType, String name) {
        super(parent, elementType, name);
    }
}
