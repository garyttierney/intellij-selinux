package com.codingmates.intellij.selinux.cil.ide.structure;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockDeclaration;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilFile;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.CilMacroDeclarationImpl;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CilStructureViewElement extends PsiTreeElementBase<NavigatablePsiElement>
        implements ItemPresentation, StructureViewTreeElement {

    protected CilStructureViewElement(NavigatablePsiElement psiElement) {
        super(psiElement);
    }

    @NotNull
    @Override
    public Collection<StructureViewTreeElement> getChildrenBase() {
        final NavigatablePsiElement element = getElement();
        final List<StructureViewTreeElement> result = new ArrayList<>();

        if (element instanceof CilFile || element instanceof CilMacroDeclarationImpl
                || element instanceof CilBlockDeclaration) {
            for (PsiElement child : element.getChildren()) {
                if (visible(child)) {
                    result.add(new CilStructureViewElement((CilDeclarationElement) child));
                }
            }
        }

        result.sort((o1, o2) -> {
            PsiElement element1, element2;
            if (o1 instanceof CilStructureViewElement &&
                    o2 instanceof CilStructureViewElement &&
                    (element1 = ((CilStructureViewElement) o1).getElement()) != null &&
                    (element2 = ((CilStructureViewElement) o2).getElement()) != null) {
                return element1.getTextRange().getStartOffset() - element2.getTextRange()
                        .getStartOffset();
            }
            return 0;
        });

        return result;
    }

    @Nullable
    @Override
    public String getPresentableText() {
        final NavigatablePsiElement element = getElement();

        return element.getName();
    }

    private boolean visible(PsiElement element) {
        return element instanceof CilBlockDeclaration || element instanceof CilMacroDeclarationImpl;
    }
}
