package com.codingmates.intellij.selinux.cil.lang.core.stubs.types;


import com.codingmates.intellij.selinux.cil.lang.core.CilLanguage;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilFile;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.impl.CilFileStub;
import com.intellij.psi.PsiFile;
import com.intellij.psi.StubBuilder;
import com.intellij.psi.stubs.DefaultStubBuilder;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CilFileStubElementType extends IStubFileElementType<CilFileStub> {

    public static final IStubFileElementType INSTANCE = new CilFileStubElementType();
    private static final int VERSION = 4;

    private CilFileStubElementType() {
        super("FILE", CilLanguage.INSTANCE);
    }

    @Override
    public StubBuilder getBuilder() {
        return new DefaultStubBuilder() {
            @NotNull
            @Override
            protected StubElement createStubForFile(@NotNull PsiFile file) {
                if (file instanceof CilFile) {
                    return new CilFileStub((CilFile) file);
                }
                return super.createStubForFile(file);
            }
        };
    }

    @Override
    public int getStubVersion() {
        return VERSION;
    }

    @Override
    public void serialize(@NotNull CilFileStub stub, @NotNull StubOutputStream dataStream)
            throws IOException {

    }

    @NotNull
    @Override
    public CilFileStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub)
            throws IOException {
        return new CilFileStub(null);
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "cil.FILE";
    }
}

