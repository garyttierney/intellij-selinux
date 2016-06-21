package com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubIndexKey;

import java.util.function.Function;

public abstract class CilDeclarationStubElementType<S extends NamedStubBase<T>, T extends CilDeclarationElement>
        extends CilStubbedStatementElementType<S, T> {

    /**
     * The index to store the respective {@link CilDeclarationElement} in.
     */
    private final StubIndexKey<String, CilDeclarationElement> indexKey;

    protected CilDeclarationStubElementType(String keyword, Function<ASTNode, T> psiFactory,
                                            StubIndexKey<String, CilDeclarationElement> indexKey) {
        super(keyword, psiFactory);
        this.indexKey = indexKey;
    }

    /**
     * Index a {@link CilDeclarationElement} using the given index key if present by its short name
     * and fully qualified name. <p> {@inheritDoc}
     */
    public void indexStub(S stub, IndexSink sink) {
        String name = stub.getName();

        if (name != null && indexKey != null) {
            sink.occurrence(indexKey, name);
        }
    }
}
