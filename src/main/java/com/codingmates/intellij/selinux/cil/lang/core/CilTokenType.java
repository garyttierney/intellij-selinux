package com.codingmates.intellij.selinux.cil.lang.core;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CilTokenType extends IElementType {
    public CilTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CilLanguage.INSTANCE);
    }
}
