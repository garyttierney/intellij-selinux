package com.codingmates.intellij.selinux.cil.lang.core.parser;

import com.intellij.AbstractBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public class CilParserMessages extends AbstractBundle {

    public static final CilParserMessages INSTANCE = new CilParserMessages();
    @NonNls
    public static final String BUNDLE = "messages.CilBundle";

    private CilParserMessages() {
        super(BUNDLE);
    }

    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
                                 @NotNull Object... params) {
        return INSTANCE.getMessage(key, params);
    }
}
