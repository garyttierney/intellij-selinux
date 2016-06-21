package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockInherit;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilBlockInheritImpl extends CilCompositeElementBase implements CilBlockInherit {

    public CilBlockInheritImpl(@NotNull ASTNode node) {
        super(node);
    }
}
