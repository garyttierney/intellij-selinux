package com.codingmates.intellij.selinux.cil.lang.core.psi;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementElementType;
import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.intellij.lang.ASTNode;

import java.util.function.Function;

public class CilNameOrderingElementType extends CilStatementElementType {

    private final CilReferenceRole nameRole;

    public CilNameOrderingElementType(String keyword, CilStatementParseHint parseHint,
                                      Function<ASTNode, CilCompositeElement> psiFactory,
                                      CilReferenceRole nameRole) {
        super(keyword, parseHint, psiFactory);
        this.nameRole = nameRole;
    }

    public CilReferenceRole getNameRole() {
        return nameRole;
    }
}
