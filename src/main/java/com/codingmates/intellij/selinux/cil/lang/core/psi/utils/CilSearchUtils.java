package com.codingmates.intellij.selinux.cil.lang.core.psi.utils;

import com.codingmates.intellij.selinux.cil.lang.core.CilFileType;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.indexing.FileBasedIndex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class CilSearchUtils {

    private CilSearchUtils() {

    }

    public static Collection<PsiElement> findTopLevelElements(Project project,
                                                              IElementType... types) {
        Collection<VirtualFile> virtualFiles =
                FileBasedIndex.getInstance()
                        .getContainingFiles(FileTypeIndex.NAME, CilFileType.INSTANCE,
                                GlobalSearchScope.allScope(project));

        List<PsiElement> elements = new ArrayList<>(20);

        for (VirtualFile virtualFile : virtualFiles) {
            CilFile file = (CilFile) PsiManager.getInstance(project).findFile(virtualFile);

            if (file != null) {
                Collections.addAll(elements, CilPsiUtils.getChildrenOfType(file, types));
            }
        }

        return elements;
    }

}
