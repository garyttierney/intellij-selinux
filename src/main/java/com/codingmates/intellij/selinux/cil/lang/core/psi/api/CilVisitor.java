package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.intellij.psi.PsiElementVisitor;

public abstract class CilVisitor extends PsiElementVisitor {

    public void visitReferenceExpression(CilReferenceExpression referenceExpression) {
        visitElement(referenceExpression);
    }
}
