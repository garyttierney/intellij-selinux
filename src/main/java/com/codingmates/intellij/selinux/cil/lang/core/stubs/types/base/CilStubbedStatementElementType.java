package com.codingmates.intellij.selinux.cil.lang.core.stubs.types.base;

import com.codingmates.intellij.selinux.cil.lang.core.CilElementFactory;
import com.codingmates.intellij.selinux.cil.lang.core.CilLanguage;
import com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementType;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;

import java.util.function.Function;

public abstract class CilStubbedStatementElementType<S extends StubElement<T>, T extends CilCompositeElement>
        extends IStubElementType<S, T> implements CilElementFactory, CilTopLevelElementType {

    private final Function<ASTNode, T> psiFactory;
    private final String keyword;

    protected CilStubbedStatementElementType(String keyword, Function<ASTNode, T> psiFactory) {
        super('<' + keyword + '>', CilLanguage.INSTANCE);
        this.keyword = keyword;
        this.psiFactory = psiFactory;
    }

    public T createElement(ASTNode node) {
        return psiFactory.apply(node);
    }

    public String getExternalId() {
        return "selinux.cil." + super.toString();
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    public void indexStub(S stub, IndexSink sink) {
    }
}
