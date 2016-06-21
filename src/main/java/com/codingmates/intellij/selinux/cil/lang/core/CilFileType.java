package com.codingmates.intellij.selinux.cil.lang.core;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CilFileType extends LanguageFileType {

    public static final CilFileType INSTANCE = new CilFileType();

    protected CilFileType() {
        super(CilLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SELinux CIL";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Common Intermediate Language for SELinux policy";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "cil";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/selinux-penguin.png");
    }
}
