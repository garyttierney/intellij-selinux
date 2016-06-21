package com.codingmates.intellij.selinux.cil.ide.codegen;

import com.codingmates.intellij.selinux.cil.lang.core.CilLanguage;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilFile;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import org.jetbrains.annotations.NotNull;

public final class CilCodeGenerationFactory {

    private CilCodeGenerationFactory() {

    }

    @NotNull
    private static CilFile createFileFromText(@NotNull Project project, @NotNull String text) {
        return (CilFile) PsiFileFactory.getInstance(project)
                .createFileFromText("internal.cil", CilLanguage.INSTANCE, text);
    }

    @NotNull
    public static PsiElement createIdentifierFromText(@NotNull Project project,
                                                      @NotNull String text) {
        CilFile fileFromText = createFileFromText(project, text + "(block " + text + ")");
        return fileFromText.findChildByClass(CilBlockDeclaration.class).getNameIdentifier();
    }
}
