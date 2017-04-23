package com.codingmates.intellij.selinux.cil.lang.core.stubs.index;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilInScope;
import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.jetbrains.annotations.NotNull;

public class CilInScopeIndex extends StringStubIndexExtension<CilInScope> {

    public static final StubIndexKey<String, CilInScope> KEY = StubIndexKey
            .createIndexKey("cil.in_scope");
    private static final int VERSION = 0;

    @Override
    public int getVersion() {
        return super.getVersion() + VERSION;
    }

    @NotNull
    public StubIndexKey<String, CilInScope> getKey() {
        return KEY;
    }
}
