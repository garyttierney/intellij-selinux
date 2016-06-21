package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.intellij.psi.tree.IElementType;

@FunctionalInterface
public interface CilReferenceRole {


    static CilReferenceRole empty() {
        return el -> false;
    }

    static CilReferenceRole match(IElementType type) {
        return type::equals;
    }

    boolean applicable(IElementType type);
}
