package com.codingmates.intellij.selinux.cil.lang.core.psi;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementElementType;
import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.util.CachedValueImpl;

import java.util.function.Function;
import java.util.function.Supplier;

public class CilNameOrderingElementType extends CilStatementElementType {

    private final CachedValue<CilReferenceRole> nameRole;

    public CilNameOrderingElementType(String keyword, CilStatementParseHint parseHint,
                                      Function<ASTNode, CilCompositeElement> psiFactory,
                                      Supplier<CilReferenceRole> nameRole) {
        super(keyword, parseHint, psiFactory);
        this.nameRole = new CachedValueImpl<>(() -> new CachedValueProvider.Result<>(nameRole.get(), ModificationTracker.NEVER_CHANGED));
    }

    public CilReferenceRole getNameRole() {
        return nameRole.getValue();
    }
}
