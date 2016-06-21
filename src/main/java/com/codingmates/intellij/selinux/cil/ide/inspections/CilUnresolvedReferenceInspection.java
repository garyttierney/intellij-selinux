package com.codingmates.intellij.selinux.cil.ide.inspections;

import com.codingmates.intellij.selinux.cil.CilBundle;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilVisitor;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public final class CilUnresolvedReferenceInspection extends LocalInspectionTool {

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly,
                                          @NotNull LocalInspectionToolSession session) {
        return new UnresolvedReferenceVisitor(holder);
    }

    private static class UnresolvedReferenceVisitor extends CilVisitor {

        private final ProblemsHolder holder;

        UnresolvedReferenceVisitor(ProblemsHolder holder) {
            this.holder = holder;
        }

        @Override
        public void visitReferenceExpression(CilReferenceExpression referenceExpression) {
            PsiElement resolve = referenceExpression.resolve();

            if (resolve == null) {
                holder.registerProblem(referenceExpression,
                        CilBundle.message("inspections.error.unresolved_reference",
                                referenceExpression.getText()), ProblemHighlightType.ERROR);
            }
        }
    }
}
