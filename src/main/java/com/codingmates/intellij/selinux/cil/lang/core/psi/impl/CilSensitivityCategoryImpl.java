package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilSensitivityCategory;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilSensitivityCategoryImpl extends CilCompositeElementBase implements
        CilSensitivityCategory {

    public CilSensitivityCategoryImpl(@NotNull ASTNode node) {
        super(node);
    }
}
