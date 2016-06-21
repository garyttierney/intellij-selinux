package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilBlockAbstract;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilBlockAbstractImpl extends CilCompositeElementBase implements CilBlockAbstract {

    public CilBlockAbstractImpl(@NotNull ASTNode node) {
        super(node);
    }
}
