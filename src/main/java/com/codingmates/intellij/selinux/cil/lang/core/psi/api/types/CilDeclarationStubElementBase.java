package com.codingmates.intellij.selinux.cil.lang.core.psi.api.types;

import com.codingmates.intellij.selinux.cil.ide.codegen.CilCodeGenerationFactory;
import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStub;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link PsiElement} which holds a name that is written to the stub index.
 *
 * @param <T> The type of {@link com.intellij.psi.stubs.Stub}.
 */
public abstract class CilDeclarationStubElementBase<T extends NamedStub<?>> extends
        CilStubbedElementBase<T>
        implements CilDeclarationElement {

    public CilDeclarationStubElementBase(@NotNull T stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public CilDeclarationStubElementBase(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        T stub = getStub();

        if (stub != null) {
            return stub.getName();
        }

        PsiElement nameIdentifier = getNameIdentifier();
        return nameIdentifier.getText();
    }

    @Override
    public PsiElement getNameIdentifier() {
        return findNotNullChildByType(CilTokenTypes.SYMBOL);
    }

    /**
     * Make sure {@code getTextOffset()} returns the correct offset for Intellij IDEA's find usages
     * provider.
     *
     * @return An adjusted offset beginning at the start of the declaration name.
     */
    @Override
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