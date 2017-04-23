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

public class CilNameAssociationElementType extends CilStatementElementType {

    private final CachedValue<CilReferenceRole> sourceRole;
    private final CachedValue<CilReferenceRole> targetRole;

    public CilNameAssociationElementType(String keyword, CilStatementParseHint parseHint,
                                         Function<ASTNode, CilCompositeElement> psiFactory,
                                         Supplier<CilReferenceRole> sourceRole, Supplier<CilReferenceRole> targetRole) {
        super(keyword, parseHint, psiFactory);

        this.sourceRole = new CachedValueImpl<>(() -> new CachedValueProvider.Result<>(sourceRole.get(), ModificationTracker.NEVER_CHANGED));
        this.targetRole = new CachedValueImpl<>(() -> new CachedValueProvider.Result<>(targetRole.get(), ModificationTracker.NEVER_CHANGED));
    }

    public CilReferenceRole getSourceRole() {
        return sourceRole.getValue();
    }

    public CilReferenceRole getTargetRole() {
        return targetRole.getValue();
    }
}
