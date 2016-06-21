package com.codingmates.intellij.selinux.cil.lang.core.psi;

import com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap;
import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.intellij.lang.regexp.RegExpLanguage;
import org.jetbrains.annotations.NotNull;

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
        CilCompositeElement parent = (CilCompositeElement) host.getParent();
        CilCompositeElement hostElement = (CilCompositeElement) host;

        return parent.getElementType() == CilTopLevelElementTypeMap.FILECON_STATEMENT
                && hostElement.getElementType() == CilTypes.STRING_EXPRESSION;
    }
}
