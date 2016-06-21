package com.codingmates.intellij.selinux.cil.ide.structure;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilMacroDeclarationImpl;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CilStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {

    private static final Sorter[] SORTERS = new Sorter[]{Sorter.ALPHA_SORTER};

    public CilStructureViewModel(@NotNull PsiFile psiFile, @Nullable Editor editor) {
        super(psiFile, editor, new CilStructureViewElement(psiFile));

        withSuitableClasses(CilBlockDeclaration.class, CilMacroDeclarationImpl.class);
    }


    @NotNull
    public Sorter[] getSorters() {
        return SORTERS;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

}
