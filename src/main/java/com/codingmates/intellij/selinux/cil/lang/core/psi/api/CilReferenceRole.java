package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public class CilReferenceRole {
    private final TokenSet types;

    public CilReferenceRole(IElementType... types) {
        this.types = TokenSet.create(types);
    }

    public boolean searchInherited() {
        return !types.contains(CilTypes.BLOCK_DECLARATION);
    }

    public static CilReferenceRole empty() {
        return new CilReferenceRole();
    }

    public static CilReferenceRole match(IElementType type) {
        return new CilReferenceRole(type);
    }

    public boolean applicable(IElementType type) {
        return types.contains(type);
    }
}
