package com.codingmates.intellij.selinux.cil.lang.core.stubs.types;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilMacroDeclarationImpl;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilMacroStub;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilAllNamesIndex;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base.CilDeclarationStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CilMacroDeclarationStubElementType extends
        CilDeclarationStubElementType<CilMacroStub, CilMacroDeclarationImpl> {

    public CilMacroDeclarationStubElementType(String debugName) {
        super(debugName, CilMacroDeclarationImpl::new, CilAllNamesIndex.KEY);
    }

    @Override
    public CilMacroDeclarationImpl createPsi(@NotNull CilMacroStub stub) {
        return new CilMacroDeclarationImpl(stub, this);
    }

    @Override
    public CilMacroStub createStub(@NotNull CilMacroDeclarationImpl psi, StubElement parentStub) {
        return new CilMacroStub(parentStub, this, psi.getName());
    }

    @NotNull
    @Override
    public CilMacroStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub)
            throws IOException {
        return new CilMacroStub(parentStub, this, dataStream.readName());
    }

    @Override
    public CilStatementParseHint getParseHint() {
        return CilStatementParseHint.MACRO_DECLARATION;
    }

    @Override
    public void serialize(@NotNull CilMacroStub stub, @NotNull StubOutputStream dataStream)
            throws IOException {
        dataStream.writeName(stub.getName());
    }
}
