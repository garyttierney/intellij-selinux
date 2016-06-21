package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilTunableIfBranch;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilTunableIfBranchImpl extends CilCompositeElementBase implements CilTunableIfBranch {

    public CilTunableIfBranchImpl(@NotNull ASTNode node) {
        super(node);
    }
}
