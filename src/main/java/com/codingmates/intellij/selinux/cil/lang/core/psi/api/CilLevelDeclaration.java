package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;

public interface CilLevelDeclaration extends CilDeclarationElement {

    CilNamedListExpression getExpression();
}
