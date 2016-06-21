package com.codingmates.intellij.selinux.cil.lang.core;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

public class CilFileTypeFactory extends FileTypeFactory {

    private CilFileType languageFileType;

    public CilFileTypeFactory() {
        this(CilFileType.INSTANCE);
    }

    public CilFileTypeFactory(CilFileType languageFileType) {
        this.languageFileType = languageFileType;
    }

    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(languageFileType, "cil");
    }
}
