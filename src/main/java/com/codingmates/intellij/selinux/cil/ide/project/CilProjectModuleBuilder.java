package com.codingmates.intellij.selinux.cil.ide.project;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.SourcePathsBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.ArrayList;
import java.util.List;

public class CilProjectModuleBuilder extends ModuleBuilder implements SourcePathsBuilder {

    /**
     * A list of source paths where policy lives for the module we're building.
     */
    private List<Pair<String, String>> sourcePaths = new ArrayList<>();

    @Override
    public void addSourcePath(Pair<String, String> sourcePathInfo) {
        sourcePaths.add(sourcePathInfo);
    }

    @Override
    public String getBuilderId() {
        return getClass().getName();
    }

    @Override
    public ModuleType getModuleType() {
        return CilProjectModuleType.getModuleType();
    }

    @Override
    public String getParentGroup() {
        return CilProjectModuleType.CIL_GROUP;
    }

    @Override
    public List<Pair<String, String>> getSourcePaths() throws ConfigurationException {
        return sourcePaths;
    }

    @Override
    public void setSourcePaths(List<Pair<String, String>> sourcePaths) {

    }

    @Override
    public void setupRootModel(ModifiableRootModel modifiableRootModel)
            throws ConfigurationException {
        if (modifiableRootModel == null) {
            throw new ConfigurationException("No root project model found");
        }

        VirtualFile root = doAddContentEntry(modifiableRootModel).getFile();
    }
}
