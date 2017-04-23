package com.codingmates.intellij.selinux.cil.lang.core.stubs.types;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilBlockDeclarationImpl;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilBlockDeclarationStub;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilAllNamesIndex;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base.CilDeclarationStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CilBlockStubElementType extends
        CilDeclarationStubElementType<CilBlockDeclarationStub, CilBlockDeclaration> {

    public CilBlockStubElementType(String debugName) {
        super(debugName, CilBlockDeclarationImpl::new, CilAllNamesIndex.KEY);
    }

    @Override
    public CilBlockDeclaration createPsi(@NotNull CilBlockDeclarationStub stub) {
        return new CilBlockDeclarationImpl(stub, this);
    }

    @Override
    public CilBlockDeclarationStub createStub(@NotNull CilBlockDeclaration psi, StubElement parentStub) {
        return new CilBlockDeclarationStub(parentStub, this, psi.getName());
    }

    @NotNull
    @Override
    public CilBlockDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub)
            throws IOException {
        return new CilBlockDeclarationStub(parentStub, this, dataStream.readName());
    }

    @Override
    public CilStatementParseHint getParseHint() {
        return CilStatementParseHint.NAMED_CONTAINER;
    }

    @Override
    public void serialize(@NotNull CilBlockDeclarationStub stub, @NotNull StubOutputStream dataStream)
            throws IOException {
        dataStream.writeName(stub.getName());
    }
}
