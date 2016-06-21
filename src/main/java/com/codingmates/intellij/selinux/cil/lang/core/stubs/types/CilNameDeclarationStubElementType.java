package com.codingmates.intellij.selinux.cil.lang.core.stubs.types;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilSymbolDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilNameDeclarationImpl;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilSymbolDeclarationStub;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilAllNamesIndex;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base.CilDeclarationStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CilNameDeclarationStubElementType extends
        CilDeclarationStubElementType<CilSymbolDeclarationStub, CilSymbolDeclaration> {

    public CilNameDeclarationStubElementType(String keyword) {
        super(keyword, CilNameDeclarationImpl::new, CilAllNamesIndex.KEY);
    }

    @Override
    public CilSymbolDeclaration createPsi(@NotNull CilSymbolDeclarationStub stub) {
        return new CilNameDeclarationImpl(stub, this);
    }

    @Override
    public CilSymbolDeclarationStub createStub(@NotNull CilSymbolDeclaration psi,
                                               StubElement parentStub) {
        return new CilSymbolDeclarationStub(parentStub, this, psi.getName());
    }

    @NotNull
    @Override
    public CilSymbolDeclarationStub deserialize(@NotNull StubInputStream dataStream,
                                                StubElement parentStub) throws IOException {
        return new CilSymbolDeclarationStub(parentStub, this, dataStream.readName());
    }

    @Override
    public CilStatementParseHint getParseHint() {
        return CilStatementParseHint.NAME_DECLARATION;
    }

    @Override
    public void serialize(@NotNull CilSymbolDeclarationStub stub,
                          @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }
}
