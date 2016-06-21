package com.codingmates.intellij.selinux.cil.lang.core.stubs.impl;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilFile;
import com.intellij.psi.stubs.PsiFileStubImpl;

public class CilFileStub extends PsiFileStubImpl<CilFile> {

    public CilFileStub(CilFile file) {
        super(file);
    }
}