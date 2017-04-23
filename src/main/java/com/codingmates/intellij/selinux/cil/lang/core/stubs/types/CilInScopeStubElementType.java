package com.codingmates.intellij.selinux.cil.lang.core.stubs.types;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilInScope;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilInScopeImpl;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilInScopeStub;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilInScopeIndex;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base.CilStubbedStatementElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.function.Function;

public class CilInScopeStubElementType extends CilStubbedStatementElementType<CilInScopeStub, CilInScope> {
    public CilInScopeStubElementType(String keyword, Function<ASTNode, CilInScope> psiFactory) {
        super(keyword, psiFactory);
    }

    @Override
    public CilStatementParseHint getParseHint() {
        return CilStatementParseHint.NAMED_CONTAINER;
    }

    @Override
    public CilInScope createPsi(@NotNull CilInScopeStub stub) {
        return new CilInScopeImpl(stub, this);
    }

    @NotNull
    @Override
    public CilInScopeStub createStub(@NotNull CilInScope psi, StubElement parentStub) {
        CilReferenceExpression expr = psi.getNamespaceReference();
        return new CilInScopeStub(parentStub, this, expr.getText());
    }

    @Override
    public void serialize(@NotNull CilInScopeStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getNamespace());
    }

    @NotNull
    @Override
    public CilInScopeStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new CilInScopeStub(parentStub, this, dataStream.readName().getString());
    }

    @Override
    public void indexStub(CilInScopeStub stub, IndexSink sink) {
        sink.occurrence(CilInScopeIndex.KEY, stub.getNamespace());
    }
}
