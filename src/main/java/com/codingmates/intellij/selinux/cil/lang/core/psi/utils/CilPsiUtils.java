package com.codingmates.intellij.selinux.cil.lang.core.psi.utils;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.google.common.base.Preconditions;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiElementFilter;
import com.intellij.psi.util.PsiTreeUtil;

public final class CilPsiUtils {

    private CilPsiUtils() {

    }

    private static PsiElement[] collectElements(PsiElement element, ElementFilter filter) {
        return PsiTreeUtil.collectElements(element, filter);
    }

    private static PsiElement[] collectElementsWithType(PsiElement element, IElementType... types) {
        TokenSet typeSet = TokenSet.create(types);

        return collectElements(element, el -> typeSet.contains(getElementType(el)));
    }

    public static PsiElement[] getChildrenOfType(PsiElement element, IElementType... types) {
        return collectElementsWithType(element, types);
    }

    private static IElementType getElementType(PsiElement element) {
        if (element instanceof CilCompositeElement) {
            return ((CilCompositeElement) element).getElementType();
        } else {
            ASTNode node = element.getNode();
            return node.getElementType();
        }
    }

    public static <T extends CilCompositeElement> T getChildAt(PsiElement parent, int offset) {
        PsiElement[] children = parent.getChildren();
        Preconditions.checkPositionIndex(offset, children.length);

        return (T) children[offset];
    }

    public static int indexOf(PsiElement parent, CilCompositeElement child) {
        PsiElement[] children = parent.getChildren();

        for (int index = 0; index < children.length; index++) {
            if (child.isEquivalentTo(children[index])) {
                return index;
            }
        }

        throw new IllegalArgumentException("Unable to find child element");
    }

    @FunctionalInterface
    private interface ElementFilter extends PsiElementFilter {

    }
}

