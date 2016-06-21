package com.codingmates.intellij.selinux.cil;

import com.intellij.AbstractBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public class CilBundle extends AbstractBundle {

    public static final String PATH_TO_BUNDLE = "messages.CilBundle";
    private static final CilBundle INSTANCE = new CilBundle();

    public CilBundle() {
        super(PATH_TO_BUNDLE);
    }

    public static String message(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key,
                                 @NotNull Object... params) {
        return INSTANCE.getMessage(key, params);
    }

}
