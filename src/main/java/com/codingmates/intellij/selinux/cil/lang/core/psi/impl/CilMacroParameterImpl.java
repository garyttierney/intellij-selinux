package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap;
import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilMacroParameter;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class CilMacroParameterImpl extends CilDeclarationElementBase implements CilMacroParameter {
    public CilMacroParameterImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public IElementType getParameterType() {
        PsiElement typeElement = getTypeElement();
        String typeValue = typeElement.getText();

        return CilTopLevelElementTypeMap.get(typeValue)
                .map(type -> (IElementType) type)
                .orElse(CilTypes.TYPE_DECLARATION);
    }

    @Override
    @NotNull
    public PsiElement getTypeElement() {
        return findNotNullChildByType(CilTokenTypes.IDENTIFIER);
    }
}
