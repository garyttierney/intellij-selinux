package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilReferenceHolderElement;

/**
 * An association between a symbol and another symbol, or list of symbols (with potentially
 * differing types).
 *
 * @author gtierney
 */
public interface CilSymbolAssociation extends CilCompositeElement, CilReferenceHolderElement {

    CilReferenceExpression getSourceSymbol();

    CilReferenceExpression getTargetSymbol();
}
