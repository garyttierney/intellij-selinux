package com.codingmates.intellij.selinux.cil.ide.project;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CilProjectModuleType extends ModuleType<CilProjectModuleBuilder> {

    public static final String CIL_GROUP = "SELinux";
    private static final String ID = "CIL_MODULE";

    public CilProjectModuleType() {
        this(ID);
    }

    protected CilProjectModuleType(@NotNull String id) {
        super(id);
    }

    public static ModuleType getModuleType() {
        return ModuleTypeManager.getInstance().findByID(ID);
    }

    @NotNull
    @Override
    public CilProjectModuleBuilder createModuleBuilder() {
        return new CilProjectModuleBuilder();
    }

    @NotNull
    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
                                                @NotNull CilProjectModuleBuilder moduleBuilder,
                                                @NotNull ModulesProvider modulesProvider) {
        return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider);
    }

    public Icon getBigIcon() {
        return AllIcons.General.Information;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "policy";
    }

    @NotNull
    @Override
    public String getName() {
        return "CIL source policy";
    }

    @Override
    public Icon getNodeIcon(@Deprecated boolean b) {
        return AllIcons.General.Information;
    }

}
