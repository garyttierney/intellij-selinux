package com.codingmates.intellij.selinux.cil.lang.core;


import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.*;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.CilNamedListItemDeclarationStubElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class CilTypes {

    public static final IElementType BINARY_EXPR = new CilCompositeElementType("binary_expr",
            CilBinaryExpressionImpl::new);
    public static final IElementType BLOCK_INHERIT_STMT = new CilCompositeElementType(
            "<blockinherit statement>", CilNameModifierImpl::new);
    public static final IElementType CALL_ARG_LIST = new CilCompositeElementType("<argument list>",
            CilCallArgumentListImpl::new);
    public static final IElementType CONTEXT_EXPR = new CilCompositeElementType("context_expr",
            CilContextExpressionImpl::new);
    public static final IElementType LEVEL_RANGE_EXPR = new CilCompositeElementType(
            "level_range_expr", CilLevelRangeExpressionImpl::new);
    public static final IElementType LIST_EXPR = new CilCompositeElementType("list_expr",
            CilListExpressionImpl::new);
    public static final IElementType MACRO_ARGUMENT = new CilCompositeElementType("macro_argument",
            CilMacroParameterImpl::new);
    public static final IElementType NAMED_LIST_ITEM_DECLARATION = new CilNamedListItemDeclarationStubElementType(
            "<named list item>");
    public static final IElementType REFERENCE_EXPR = new CilCompositeElementType("reference_expr",
            CilReferenceExpressionImpl::new);
    public static final IElementType STRING_EXPRESSION = new CilCompositeElementType(
            "<string expression>", CilStringExpressionImpl::new);
    public static final IElementType SYMBOL_AND_LIST_EXPR = new CilCompositeElementType(
            "class_permission_expr", CilNamedListExpressionImpl::new);
    public static final IElementType TUNABLE_IF_BRANCH = new CilCompositeElementType(
            "<tunable conditional branch>", CilTunableIfBranchImpl::new);
    public static final IElementType UNARY_EXPR = new CilCompositeElementType("<unary expression>",
            CilUnaryExpressionImpl::new);

    public static class CilCompositeElementType extends IElementType implements CilElementFactory {

        private Function<ASTNode, ? extends CilCompositeElement> psiElementFactory;

        public CilCompositeElementType(@NotNull @NonNls String debugName,
                                       Function<ASTNode, ? extends CilCompositeElement> psiElementFactory) {
            super(debugName, CilLanguage.INSTANCE);
            this.psiElementFactory = psiElementFactory;
        }

        public CilCompositeElement createElement(ASTNode node) {
            return psiElementFactory.apply(node);
        }

    }
}
