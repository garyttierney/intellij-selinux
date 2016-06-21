package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.CilFileType;
import com.codingmates.intellij.selinux.cil.lang.core.CilLanguage;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilContainerElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CilFile extends PsiFileBase implements CilContainerElement {

    public CilFile(FileViewProvider fileViewProvider) {
        super(fileViewProvider, CilLanguage.INSTANCE);
    }

    public Collection<CilDeclarationElement> getDeclarations() {
        return null;
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return CilFileType.INSTANCE;
    }
}
