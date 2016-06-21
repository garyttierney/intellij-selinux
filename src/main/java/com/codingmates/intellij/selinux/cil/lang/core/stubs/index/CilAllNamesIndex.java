package com.codingmates.intellij.selinux.cil.lang.core.stubs.index;


import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.jetbrains.annotations.NotNull;

public class CilAllNamesIndex extends StringStubIndexExtension<CilDeclarationElement> {

    public static final StubIndexKey<String, CilDeclarationElement> KEY = StubIndexKey
            .createIndexKey("cil.all.name");
    private static final int VERSION = 0;

    @Override
    public int getVersion() {
        return super.getVersion() + VERSION;
    }

    @NotNull
    public StubIndexKey<String, CilDeclarationElement> getKey() {
        return KEY;
    }
}
