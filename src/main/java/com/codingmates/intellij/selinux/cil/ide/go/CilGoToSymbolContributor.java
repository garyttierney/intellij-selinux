package com.codingmates.intellij.selinux.cil.ide.go;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilDeclarationElement;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.index.CilAllNamesIndex;
import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StubIndex;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * A go-to symbol contributor which provides symbols from an index of all container declarations
 * found in the search scope.
 *
 * @author gtierney
 */
public class CilGoToSymbolContributor implements ChooseByNameContributor {

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project,
                                           boolean includeNonProjectItems) {
        GlobalSearchScope scope = includeNonProjectItems ? GlobalSearchScope.allScope(project)
                : GlobalSearchScope.projectScope(project);

        Collection<CilDeclarationElement> result = StubIndex
                .getElements(CilAllNamesIndex.KEY, name, project,
                        scope, CilDeclarationElement.class);

        List<NavigationItem> items = ContainerUtil.newArrayListWithCapacity(result.size());
        items.addAll(result);

        return items.toArray(new NavigationItem[items.size()]);
    }

    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        return ArrayUtil
                .toStringArray(StubIndex.getInstance().getAllKeys(CilAllNamesIndex.KEY, project));
    }
}
