package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilTunable;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CilTunableDeclarationImpl extends CilCompositeElementBase implements CilTunable {

    public CilTunableDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }
}
