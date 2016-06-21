package com.codingmates.intellij.selinux.cil.lang.core.psi.api.types;

import com.codingmates.intellij.selinux.cil.ide.codegen.CilCodeGenerationFactory;
import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public abstract class CilDeclarationElementBase extends CilCompositeElementBase implements
        CilDeclarationElement {

    public CilDeclarationElementBase(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    public PsiElement getNameIdentifier() {
        return findNotNullChildByType(CilTokenTypes.SYMBOL);
    }

    /**
     * Make sure {@code getTextOffset()} returns the correct offset for Intellij IDEA's find usages
     * provider.
     *
     * @return An adjusted offset beginning at the start of the declaration name.
     */
    public int getTextOffset() {
        return getNameIdentifier().getTextOffset();
    }


    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        PsiElement oldIdentifier = getNameIdentifier();
        PsiElement newIdentifier = CilCodeGenerationFactory
                .createIdentifierFromText(getProject(), name);

        return oldIdentifier.replace(newIdentifier);
    }
}
