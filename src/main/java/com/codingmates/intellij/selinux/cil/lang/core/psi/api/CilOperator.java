package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.google.common.base.Enums;

public enum CilOperator {
    /**
     * Used in constraint and MLS constraint expressions to compare equality of roles, users, types,
     * or sensitivities.
     */
    EQ,
    NEQ,
    DOM,
    DOMBY,
    INCOMP,
    AND,
    OR,
    XOR,

    /**
     * MCS category range operator.
     */
    RANGE,
    NOT(true),
    INVALID;

    private final boolean unary;

    CilOperator() {
        this(false);
    }

    CilOperator(boolean unary) {
        this.unary = unary;
    }

    public static CilOperator from(String name) {
        return Enums.getIfPresent(CilOperator.class, name.toUpperCase()).or(INVALID);
    }

    public boolean isUnary() {
        return unary;
    }
}
