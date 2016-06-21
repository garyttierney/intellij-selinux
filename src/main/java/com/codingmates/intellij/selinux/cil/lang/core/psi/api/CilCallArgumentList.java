package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

import java.util.List;

public interface CilCallArgumentList extends CilReferenceHolderElement {

    List<CilExpression> getArguments();
}
