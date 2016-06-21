package com.codingmates.intellij.selinux.cil.lang.core;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

import java.util.function.Function;

public class CilStatementElementType extends IElementType implements CilTopLevelElementType {

    private final String keyword;
    private final CilStatementParseHint parseHint;
    private final Function<ASTNode, CilCompositeElement> psiFactory;

    public CilStatementElementType(String keyword, CilStatementParseHint parseHint,
                                   Function<ASTNode, CilCompositeElement> psiFactory) {
        super('<' + keyword + '>', CilLanguage.INSTANCE);
        this.keyword = keyword;
        this.parseHint = parseHint;
        this.psiFactory = psiFactory;
    }

    @Override
    public CilCompositeElement createElement(ASTNode astNode) {
        return psiFactory.apply(astNode);
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public CilStatementParseHint getParseHint() {
        return parseHint;
    }
}
