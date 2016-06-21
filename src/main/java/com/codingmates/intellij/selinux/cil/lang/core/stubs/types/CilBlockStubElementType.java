package com.codingmates.intellij.selinux.cil.lang.core.stubs.types;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilBlockDeclarationImpl;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilBlockStub;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilAllNamesIndex;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base.CilDeclarationStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CilBlockStubElementType extends
        CilDeclarationStubElementType<CilBlockStub, CilBlockDeclaration> {

    public CilBlockStubElementType(String debugName) {
        super(debugName, CilBlockDeclarationImpl::new, CilAllNamesIndex.KEY);
    }

    @Override
    public CilBlockDeclaration createPsi(@NotNull CilBlockStub stub) {
        return new CilBlockDeclarationImpl(stub, this);
    }

    @Override
    public CilBlockStub createStub(@NotNull CilBlockDeclaration psi, StubElement parentStub) {
        return new CilBlockStub(parentStub, this, psi.getName());
    }

    @NotNull
    @Override
    public CilBlockStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub)
            throws IOException {
        return new CilBlockStub(parentStub, this, dataStream.readName());
    }

    @Override
    public CilStatementParseHint getParseHint() {
        return CilStatementParseHint.NAMED_CONTAINER;
    }

    @Override
    public void serialize(@NotNull CilBlockStub stub, @NotNull StubOutputStream dataStream)
            throws IOException {
        dataStream.writeName(stub.getName());
    }
}
