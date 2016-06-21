package com.codingmates.intellij.selinux.cil.lang.core.psi.api.types;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilFile;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilMacroDeclaration;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CilCompositeElementBase extends ASTWrapperPsiElement implements
        CilCompositeElement {

    public CilCompositeElementBase(@NotNull ASTNode node) {
        super(node);
    }

    /**
     * Adjust an element offset if a given {@code optional} is present with an offset of {@code
     * optionalOffset}.
     *
     * @param optionalOffset The offset of the optional element.
     * @param offset         The offset to adjust.
     * @return An adjusted offset depending on whether the optional element is present.
     */
    protected final int adjustedOptionalOffset(Optional optional, int optionalOffset, int offset) {
        int delta = optional.isPresent() ? 1 : 0;

        return offset >= optionalOffset ? offset + delta : offset;
    }

    public PsiElement getContext() {
        return PsiTreeUtil.getParentOfType(this, CilBlockDeclaration.class,
                CilFile.class, CilMacroDeclaration.class);
    }

    public PsiElement[] getChildren() {
        List<PsiElement> children = new ArrayList<>();

        PsiElement child = getFirstChild();
        PsiElement last = getLastChild();

        while (child != null) {
            if (child instanceof CilCompositeElement) {
                children.add(child);
            }

            if (last.isEquivalentTo(child)) {
                break;
            }

            child = child.getNextSibling();
        }

        return children.toArray(new PsiElement[0]);
    }

    public final IElementType getElementType() {
        if (this instanceof StubElement) {
            return ((StubElement) this).getStubType();
        } else {
            return getNode().getElementType();
        }
    }
}
