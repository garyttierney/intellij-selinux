package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilMacroParameter;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilMacroParameterImpl extends CilDeclarationElementBase implements CilMacroParameter {
    public CilMacroParameterImpl(@NotNull ASTNode node) {
        super(node);
    }
}
