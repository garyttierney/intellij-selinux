package com.codingmates.intellij.selinux.cil.lang.core.psi.utils;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.*;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilAllNamesIndex;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StubIndex;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap.BLOCKINHERIT_STATEMENT;

/**
 * Utilities for resolving references and walking namespace trees.
 *
 * @author gtierney
 */
public final class CilResolveUtil {

    private CilResolveUtil() {
    }

    public static boolean processDeclarationRecursive(CilCompositeElement o,
                                                      @NotNull PsiScopeProcessor processor,
                                                      @NotNull ResolveState state) {
        Queue<CilCompositeElement> queue = new LinkedList<>();
        queue.add(o);

        while (!queue.isEmpty()) {
            CilCompositeElement top = queue.remove();
            if (!processor.execute(top, state)) {
                return false;
            }

            queue.addAll(PsiTreeUtil.getChildrenOfTypeAsList(top, CilCompositeElement.class));
        }

        return true;
    }

    /**
     * Walk the global scope and search for any top level references to {@code nameElement}.
     *
     * @param processor
     * @param origin
     * @param nameElement
     * @param searchInherited
     */
    public static void walkGlobalScope(PsiScopeProcessor processor, CilCompositeElement origin, PsiElement nameElement,
                                       boolean searchInherited) {
        Project project = origin.getProject();
        GlobalSearchScope searchScope = GlobalSearchScope.projectScope(project);

        //@todo - searchInherited support for global scope
        StubIndex.getElements(CilAllNamesIndex.KEY, nameElement.getText(), project, searchScope, CilDeclarationElement.class)
                .forEach(el -> processor.execute(el, ResolveState.initial()));
    }

    /**
     * Begin processing declarations by walking up the scope hierarchy from where {@code origin} occurs.
     *
     * @param processor       The {@code PsiScopeProcessor} to process declarations with.
     * @param origin          The origin of the search.
     * @param searchInherited Whether to process inherited namespaces.
     */
    public static void walkUpScope(PsiScopeProcessor processor, CilCompositeElement origin, boolean searchInherited) {
        PsiElement scope = origin.getContext();

        while (scope != null) {
            if (!walkScope(processor, scope, searchInherited)) {
                return;
            }

            scope = scope.getContext();
        }
    }

    /**
     * Process declarations in the given {@code scope}.
     *
     * @param processor The {@code PsiScopeProcessor} to process declarations.
     * @param scope     The scope to process declarations in.
     * @return {@code true} to continue processing declarations, {@code false} to finish.
     */
    private static boolean processScopedDeclarations(PsiScopeProcessor processor, PsiElement scope, ResolveState state) {
        if (!(scope instanceof CilFile) && !processor.execute(scope, state)) {
            return false;
        }

        List<CilDeclarationElement> currentScopeDeclarations = PsiTreeUtil.getChildrenOfTypeAsList(
                scope, CilDeclarationElement.class);

        for (CilDeclarationElement declaration : currentScopeDeclarations) {
            if (!processor.execute(declaration, state)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Walk all declarations in the given {@code scope} and optionally search inherited scopes if {@code searchInherited}
     * is {@code true}.
     *
     * @param processor       The element processor.
     * @param scope           The scope to search.
     * @param searchInherited A flag indicating whether to search inherited namespaces.
     * @return {@code true} to keep searching, {@code false} to stop.
     */
    public static boolean walkScope(PsiScopeProcessor processor, PsiElement scope, boolean searchInherited) {
        ResolveState state = ResolveState.initial();

        if (!processScopedDeclarations(processor, scope, state)) {
            return false;
        }

        if (scope instanceof CilMacroDeclaration) {
            CilMacroDeclaration macroDeclaration = (CilMacroDeclaration) scope;
            List<CilMacroParameter> macroParameters = macroDeclaration.getParameters();

            for (PsiElement param : macroParameters) {
                if (!processor.execute(param, state)) {
                    return false;
                }
            }
        }

        if (searchInherited) {
            PsiElement[] blockInheritElements = CilPsiUtils.getChildrenOfType(scope, BLOCKINHERIT_STATEMENT);

            for (PsiElement element : blockInheritElements) {
                CilNameModifier statement = (CilNameModifier) element;
                CilReferenceExpression blockReference = statement.getNameReference();
                PsiElement block = blockReference.resolve();

                if (block != null && !walkScope(processor, block, true)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Optional<PsiElement> resolve(CilReferenceExpression reference) {
        return Optional.ofNullable(reference.resolve());
    }
}
