package com.codingmates.intellij.selinux.cil.lang.core.stubs.types;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementType;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilNamedListItemDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilNamedListItemDeclarationImpl;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilNamedListItemDeclarationStub;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilAllNamesIndex;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base.CilDeclarationStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

//@todo not really a top level element type, but still a declaration
public class CilNamedListItemDeclarationStubElementType extends
        CilDeclarationStubElementType<CilNamedListItemDeclarationStub, CilNamedListItemDeclaration>
        implements CilTopLevelElementType {

    public CilNamedListItemDeclarationStubElementType(String debugName) {
        super(debugName, CilNamedListItemDeclarationImpl::new, CilAllNamesIndex.KEY);
    }

    @Override
    public CilNamedListItemDeclaration createPsi(@NotNull CilNamedListItemDeclarationStub stub) {
        return new CilNamedListItemDeclarationImpl(stub, this);
    }

    @Override
    public CilNamedListItemDeclarationStub createStub(@NotNull CilNamedListItemDeclaration psi,
                                                      StubElement parentStub) {
        return new CilNamedListItemDeclarationStub(parentStub, this, psi.getName());
    }

    @NotNull
    @Override
    public CilNamedListItemDeclarationStub deserialize(@NotNull StubInputStream dataStream,
                                                       StubElement parentStub) throws IOException {
        return new CilNamedListItemDeclarationStub(parentStub, this, dataStream.readName());
    }

    @Override
    public String getKeyword() {
        return null;
    }

    @Override
    public CilStatementParseHint getParseHint() {
        return CilStatementParseHint.NAMED_LIST_DECLARATION;
    }

    @Override
    public void serialize(@NotNull CilNamedListItemDeclarationStub stub,
                          @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }
}
