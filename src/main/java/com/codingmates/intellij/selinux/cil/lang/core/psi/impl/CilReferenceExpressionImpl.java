package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.*;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.references.CilDeclarationProcessor;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilResolveUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.BLOCK_DECLARATION_REF;
import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.CONTAINER_DECLARATIONS;

public class CilReferenceExpressionImpl extends CilReferenceElementBase implements
        CilReferenceExpression {

    public static final String INVALID_REF_ERROR = "Found a reference (type=%s) without a parent (type=%s) providing reference roles";

    public CilReferenceExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public void accept(@NotNull CilVisitor visitor) {
        visitor.visitReferenceExpression(this);
    }

    @Override
    public <T> T accept(CilExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    /**
     * Check if this is a reference to a declaration in the global namespace, specified by a leading dot.
     *
     * @return {@code true} if the reference is to a declaration to in the global namespace.
     */
    public boolean isGlobalReference() {
        return !isQualified() && findChildByType(CilTokenTypes.DOT) != null;
    }

    /**
     * Check if this reference is qualified by a preceding namespace reference expression.
     *
     * @return {@code true} if this reference is qualified.
     */
    public boolean isQualified() {
        return findChildByType(CilTypes.REFERENCE_EXPR) != null;
    }

    /**
     * Get an optional reference which qualifies the scope of this reference.
     *
     * @return An optional qualifier.
     */
    public Optional<CilReferenceExpression> getQualifier() {
        return Optional.ofNullable(findChildByType(CilTypes.REFERENCE_EXPR));
    }

    /**
     * Attempt to resolve the {@link CilReferenceRole} of this expression by searching for an ancestor
     * {@link CilReferenceHolderElement} and requesting the child role of the root expression this
     * reference is used in.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public CilReferenceRole getRole() {
        if (isQualifier()) {
            return BLOCK_DECLARATION_REF;
        }

        CilCompositeElement parent = (CilCompositeElement) getParent();
        IElementType parentType = parent.getElementType();
        IElementType thisType = getElementType();

        CilListExpression namedListExpr = PsiTreeUtil.getParentOfType(this, CilListExpression.class);

        if (namedListExpr != null) {
            return CilReferenceRole.match(CilTypes.NAMED_LIST_ITEM_DECLARATION);
        }

        CilReferenceHolderElement referenceHolder = PsiTreeUtil.getParentOfType(this,
                CilReferenceHolderElement.class);

        if (referenceHolder == null) {
            throw new IllegalStateException(String.format(INVALID_REF_ERROR, thisType, parentType));
        }

        CilExpression rootExpression = parent.isEquivalentTo(referenceHolder) ? this
                : PsiTreeUtil.getTopmostParentOfType(this, CilExpression.class);

        if (rootExpression == null) {
            throw new IllegalStateException("Unable to identify root expression for reference");
        }

        return referenceHolder.getChildRole(rootExpression.getElementType(), CilPsiUtils.indexOf(referenceHolder, rootExpression));
    }

    public boolean isQualifier() {
        CilCompositeElement parent = (CilCompositeElement) getParent();
        return parent.getElementType() == CilTypes.REFERENCE_EXPR;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return Arrays.stream(tryResolve(incompleteCode))
                .map(PsiElementResolveResult::new)
                .toArray(ResolveResult[]::new);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] results = multiResolve(false);
        return results.length > 0 ? results[0].getElement() : null;
    }

    @NotNull
    @Override
    public PsiElement[] tryResolve(boolean incompleteCode) {
        List<CilDeclarationElement> results = new ArrayList<>();

        PsiElement nameElement = findNotNullChildByType(CilTokenTypes.IDENTIFIER);
        CilCompositeElement parent = (CilCompositeElement) getParent();
        CilReferenceRole nameRole = getRole();

        PsiScopeProcessor processor = new CilDeclarationProcessor(nameElement, nameRole, parent,
                incompleteCode, results::add);

        CilListExpression listExpr = PsiTreeUtil.getParentOfType(this, CilListExpression.class);
        CilNamedListExpression namedListExpr = PsiTreeUtil.getParentOfType(this, CilNamedListExpression.class);

        if (namedListExpr != null && listExpr != null) {
            CilReferenceExpression listQualifierReference = namedListExpr.getListReference();
            CilCompositeElement listQualifier = (CilCompositeElement) listQualifierReference.resolve();

            if (listQualifier == null) {
                return PsiElement.EMPTY_ARRAY;
            }

            CilResolveUtil.processDeclarationRecursive(listQualifier, processor, ResolveState.initial());
            return results.toArray(PsiElement.EMPTY_ARRAY);
        }

        boolean isGlobalReference = isGlobalReference();
        boolean isQualified = isQualified();
        boolean searchInherited = nameRole != BLOCK_DECLARATION_REF && nameRole != CONTAINER_DECLARATIONS;

        if (isGlobalReference) {
            CilResolveUtil.walkGlobalScope(processor, parent, nameElement, searchInherited);
        } else if (isQualified) {
            Optional<PsiElement> scope = getQualifier().map(CilReferenceExpression::resolve);
            scope.ifPresent(_scope -> CilResolveUtil.walkScope(processor, _scope, searchInherited));
        } else {
            CilResolveUtil.walkUpScope(processor, parent, searchInherited);
            if (results.isEmpty()) {
                CilResolveUtil.walkGlobalScope(processor, parent, nameElement, searchInherited);
            }
        }

        return results.toArray(PsiElement.EMPTY_ARRAY);
    }
}
