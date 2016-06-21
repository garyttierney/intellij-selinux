package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.google.common.base.Enums;

public enum CilTransitionRuleType {
    TYPEMEMBER,
    TYPECHANGE,
    TYPETRANSITION,
    ROLETRANSITION,
    INVALID;

    public static CilTransitionRuleType from(String value) {
        return Enums.getIfPresent(CilTransitionRuleType.class, value)
                .or(INVALID);
    }
}
