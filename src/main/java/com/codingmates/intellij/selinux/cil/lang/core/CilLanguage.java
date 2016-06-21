package com.codingmates.intellij.selinux.cil.lang.core;

import com.intellij.lang.Language;

public class CilLanguage extends Language {

    public static final CilLanguage INSTANCE = new CilLanguage();

    protected CilLanguage() {
        super("cil");
    }
}
