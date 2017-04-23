package com.codingmates.intellij.selinux.cil.lang.core.stubs.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilInScope;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import org.jetbrains.annotations.NotNull;

public class CilInScopeStub extends StubBase<CilInScope> {

    private final String namespace;

    public CilInScopeStub(StubElement parent, @NotNull IStubElementType elementType, String namespace) {
        super(parent, elementType);
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }
}
