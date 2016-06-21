package com.codingmates.intellij.selinux.cil.lang.core.psi;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilStringExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;


public class CilStringLiteralManipulator extends AbstractElementManipulator<CilStringExpression> {

    @Override
    public CilStringExpression handleContentChange(@NotNull CilStringExpression element,
                                                   @NotNull TextRange range, String newContent) throws IncorrectOperationException {
        PsiElement stringToken = element.getStringLiteral();
        if (!(stringToken instanceof LeafElement)) {
            throw new IllegalStateException("String literal is not a leaf elemetn");
        }

        LeafElement leaf = (LeafElement) stringToken;
        leaf.replaceWithText('"' + newContent + '"');
        return element;
    }
}
