package com.codingmates.intellij.selinux.cil.lang.core.psi;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.intellij.lang.regexp.RegExpLanguage;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap.FILECON_STATEMENT;

public final class CilStringLiteralRegexInjector implements LanguageInjector {

    @Override
    public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host,
                                     @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
        if (isValidTarget(host)) {
            injectionPlacesRegistrar.addPlace(RegExpLanguage.INSTANCE, new TextRange(1, host.getTextLength() - 1),
                    null, null);
        }
    }

    private boolean isValidTarget(PsiLanguageInjectionHost host) {
        PsiElement parent = host.getParent();

        if (parent instanceof CilCompositeElement && host instanceof CilCompositeElement) {
            CilCompositeElement parentElement = (CilCompositeElement) parent;
            CilCompositeElement hostElement = (CilCompositeElement) host;

            return parentElement.getElementType() == FILECON_STATEMENT
                    && hostElement.getElementType() == CilTypes.STRING_EXPRESSION;
        }

        return false;
    }
}
