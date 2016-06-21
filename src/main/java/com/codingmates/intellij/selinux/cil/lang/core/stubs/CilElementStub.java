package com.codingmates.intellij.selinux.cil.lang.core.stubs;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;

public class CilElementStub<T extends CilCompositeElement> extends StubBase<T> {

    protected CilElementStub(StubElement parent, IStubElementType elementType) {
        super(parent, elementType);
    }
}
