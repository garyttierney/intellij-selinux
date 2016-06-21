package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;

public interface CilLevelRangeDeclaration extends CilCompositeElement, CilDeclarationElement {

    CilLevelRangeExpression getExpression();
}
