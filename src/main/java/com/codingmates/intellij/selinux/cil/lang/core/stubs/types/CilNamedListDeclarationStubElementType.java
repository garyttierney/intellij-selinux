package com.codingmates.intellij.selinux.cil.lang.core.stubs.types;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilNamedListDeclarationImpl;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilNamedListDeclarationStub;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilAllNamesIndex;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base.CilDeclarationStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CilNamedListDeclarationStubElementType extends
        CilDeclarationStubElementType<CilNamedListDeclarationStub, CilNamedListDeclaration> {

    public CilNamedListDeclarationStubElementType(String debugName) {
        super(debugName, CilNamedListDeclarationImpl::new, CilAllNamesIndex.KEY);
    }

    @Override
    public CilNamedListDeclaration createPsi(@NotNull CilNamedListDeclarationStub stub) {
        return new CilNamedListDeclarationImpl(stub, this);
    }

    @Override
    public CilNamedListDeclarationStub createStub(@NotNull CilNamedListDeclaration psi,
                                                  StubElement parentStub) {
        return new CilNamedListDeclarationStub(parentStub, this, psi.getName());
    }

    @NotNull
    @Override
    public CilNamedListDeclarationStub deserialize(@NotNull StubInputStream dataStream,
                                                   StubElement parentStub) throws IOException {
        return new CilNamedListDeclarationStub(parentStub, this, dataStream.readName());
    }

    @Override
    public CilStatementParseHint getParseHint() {
        return CilStatementParseHint.NAMED_LIST_DECLARATION;
    }

    @Override
    public void serialize(@NotNull CilNamedListDeclarationStub stub,
                          @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }
}
