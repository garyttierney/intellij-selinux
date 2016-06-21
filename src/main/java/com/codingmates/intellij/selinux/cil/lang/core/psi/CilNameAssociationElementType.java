package com.codingmates.intellij.selinux.cil.lang.core.psi;

import com.codingmates.intellij.selinux.cil.lang.core.CilStatementElementType;
import com.codingmates.intellij.selinux.cil.lang.core.CilStatementParseHint;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.intellij.lang.ASTNode;

import java.util.function.Function;

public class CilNameAssociationElementType extends CilStatementElementType {

    private final CilReferenceRole sourceRole;
    private final CilReferenceRole targetRole;

    public CilNameAssociationElementType(String keyword, CilStatementParseHint parseHint,
                                         Function<ASTNode, CilCompositeElement> psiFactory,
                                         CilReferenceRole sourceRole, CilReferenceRole targetRole) {
        super(keyword, parseHint, psiFactory);
        this.sourceRole = sourceRole;
        this.targetRole = targetRole;
    }

    public CilReferenceRole getSourceRole() {
        return sourceRole;
    }

    public CilReferenceRole getTargetRole() {
        return targetRole;
    }
}
