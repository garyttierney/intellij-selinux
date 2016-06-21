package com.codingmates.intellij.selinux.cil.lang.core.syntax;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class CilSyntaxAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        ASTNode node = element.getNode();
        IElementType elementType = node.getElementType();

        if (elementType == CilTokenTypes.STATEMENT_KEYWORD
                || elementType == CilTokenTypes.OPERATOR) {
            holder.createInfoAnnotation(element, null)
                    .setTextAttributes(CilSyntaxHighlighter.KEYWORD);
        }
    }
}
