package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import java.util.Optional;

public final class CilExpressionContext {

    private final CilReferenceRole referenceRole;
    private final CilReferenceRole listReferenceRole;

    public CilExpressionContext(CilReferenceRole referenceRole) {
        this(referenceRole, null);
    }

    public CilExpressionContext(CilReferenceRole referenceRole,
                                CilReferenceRole listReferenceRole) {
        this.referenceRole = referenceRole;
        this.listReferenceRole = listReferenceRole;
    }

    public Optional<CilReferenceRole> getListReferenceRole() {
        return Optional.ofNullable(listReferenceRole);
    }

    public CilReferenceRole getReferenceRole() {
        return referenceRole;
    }
}
