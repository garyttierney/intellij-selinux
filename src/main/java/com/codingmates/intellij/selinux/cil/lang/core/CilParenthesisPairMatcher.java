package com.codingmates.intellij.selinux.cil.lang.core;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CilParenthesisPairMatcher implements PairedBraceMatcher {

    @Override
    public BracePair[] getPairs() {
        return new BracePair[]{new CilParenthesisPair()};
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType,
                                                   @Nullable IElementType contextType) {
        return false;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset + 1;
    }
}
