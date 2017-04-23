package com.codingmates.intellij.selinux.cil.lang.core.psi.api.types;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilExpressionVisitor;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public abstract class CilReferenceElementBase extends CilCompositeElementBase implements PsiReference, PsiElement {

    public CilReferenceElementBase(@NotNull ASTNode node) {
        super(node);
    }

    public abstract void accept(@NotNull CilVisitor visitor);

    public abstract <T> T accept(CilExpressionVisitor<T> visitor);

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof CilVisitor) {
            accept((CilVisitor) visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element)
            throws IncorrectOperationException {
        return null;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return getText();
    }

    @Override
    public PsiElement getElement() {
        return this;
    }

    @Override
    public TextRange getRangeInElement() {
        PsiElement id = findNotNullChildByType(CilTokenTypes.IDENTIFIER);
        return new TextRange(id.getStartOffsetInParent(),
                id.getStartOffsetInParent() + id.getTextLength());
    }

    @Override
    public PsiReference getReference() {
        return this;
    }

    @Override
    public PsiReference[] getReferences() {
        return new PsiReference[]{this};
    }

    @NotNull
    @Override
    public PsiElement[] getVariants() {
        return tryResolve(true);
    }

    @Override
    public PsiElement handleElementRename(String newElementName)
            throws IncorrectOperationException {
        return null;
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        return getManager().areElementsEquivalent(resolve(), element);
    }

    @Override
    public boolean isSoft() {
        return false;
    }

    public abstract PsiElement[] tryResolve(boolean incompleteCode);

}
