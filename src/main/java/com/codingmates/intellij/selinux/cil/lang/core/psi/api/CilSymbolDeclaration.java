package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilSymbolDeclarationStub;
import com.intellij.psi.StubBasedPsiElement;

/**
 * A declaration of a single typed symbol, with a type from {@link CilDeclarationType}.
 *
 * @author gtierney
 */
public interface CilSymbolDeclaration extends CilDeclarationElement,
        StubBasedPsiElement<CilSymbolDeclarationStub> {

}
