package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;

import java.util.List;

/**
 * A declaration of a symbol with a list as its argument, used by {@code class} and {@code common}
 * statements.
 *
 * @author gtierney
 */
public interface CilNamedListDeclaration extends CilDeclarationElement {

    List<CilNamedListItemDeclaration> getItems();
}
