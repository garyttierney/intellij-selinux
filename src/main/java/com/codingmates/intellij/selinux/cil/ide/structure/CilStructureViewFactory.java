package com.codingmates.intellij.selinux.cil.ide.structure;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CilStructureViewFactory implements PsiStructureViewFactory {

    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder(@NotNull PsiFile psiFile) {
        return new CilStructureViewBuilder(psiFile);
    }

    private static class CilStructureViewBuilder extends TreeBasedStructureViewBuilder {

        private final PsiFile psiFile;

        public CilStructureViewBuilder(PsiFile psiFile) {
            this.psiFile = psiFile;
        }

        @NotNull
        @Override
        public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
            return new CilStructureViewModel(psiFile, editor);
        }

        @Override
        public boolean isRootNodeShown() {
            return false;
        }
    }
}
