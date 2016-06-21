package com.codingmates.intellij.selinux.cil.lang.core.parser;

import com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public final class CilParser implements PsiParser {

    private final CilTopLevelElementTypeMap toplevelElementMap = new CilTopLevelElementTypeMap();

    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        final PsiBuilder.Marker rootMarker = builder.mark();
        final CilStatementParser statementParser = new CilStatementParser(toplevelElementMap,
                builder);

        statementParser.parseStatements();

        rootMarker.done(root);
        return builder.getTreeBuilt();
    }
}
