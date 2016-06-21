package com.codingmates.intellij.selinux.cil.lang.core.psi.references;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.BaseScopeProcessor;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * An element processor which handles processing declarations for references and code-completion.
 *
 * @author gtierney
 */
public class CilDeclarationProcessor extends BaseScopeProcessor {

    /**
     * Placeholder used in place of an identifier when resolving completion contributors.
     * <p>
     * XXX: is this necessary?
     */
    private static final String INCOMPLETE_CODE_PLACEHOLDER = "IntellijIdeaRulezzz";

    private final PsiElement nameElement;
    private final CilReferenceRole nameRole;
    private final PsiElement origin;
    private final boolean incompleteCode;
    private final Consumer<CilDeclarationElement> resultConsumer;

    public CilDeclarationProcessor(@NotNull PsiElement nameElement,
                                   @NotNull CilReferenceRole nameRole,
                                   @NotNull CilCompositeElement origin,
                                   boolean incompleteCode,
                                   Consumer<CilDeclarationElement> resultConsumer) {
        this.nameElement = nameElement;
        this.nameRole = nameRole;
        this.origin = origin;
        this.incompleteCode = incompleteCode;
        this.resultConsumer = resultConsumer;
    }

    private boolean elementMatches(CilDeclarationElement declaration) {
        String name = nameElement.getText();
        String declarationName = declaration.getName();

        if (incompleteCode) {
            return declarationName.contains(name.replace(INCOMPLETE_CODE_PLACEHOLDER, ""));
        } else {
            return nameElement.textMatches(declarationName);
        }
    }

    @Override
    public boolean execute(@NotNull PsiElement element, @NotNull ResolveState state) {
        CilCompositeElement compositeElement = (CilCompositeElement) element;
        IElementType elementType = compositeElement.getElementType();

        if (origin.isEquivalentTo(element)) {
            return true;
        }

        if (nameRole.applicable(elementType)) {
            CilDeclarationElement decl = (CilDeclarationElement) element;

            if (elementMatches(decl)) {
                resultConsumer.accept(decl);
                return incompleteCode;
            }
        }

        return true;
    }
}
