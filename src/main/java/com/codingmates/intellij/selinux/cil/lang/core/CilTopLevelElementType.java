package com.codingmates.intellij.selinux.cil.lang.core;

import com.intellij.psi.impl.source.tree.ElementType;

public interface CilTopLevelElementType extends ElementType, CilElementFactory {

    String getKeyword();

    CilStatementParseHint getParseHint();
}
