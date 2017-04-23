package com.codingmates.intellij.selinux.cil.lang.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.intellij.psi.impl.source.tree.ElementType;
import com.intellij.psi.tree.IElementType;

import java.util.Optional;

/**
 * A mapping of statement keywords to {@link ElementType}s.
 *
 * @author gtierney
 */
public final class CilTopLevelElementTypeMap {

    ;


    /**
     * A mapping of statement keywords to {@link CilTopLevelElementType} containing parse hints and
     * {@link ElementType} information.
     */
    private static final ImmutableMap<String, CilTopLevelElementType> keywordToTypeMap;

    static {
        ImmutableMap.Builder<String, CilTopLevelElementType> keywordToTypeMapBuilder = ImmutableMap
                .builder();

        appendClassAndPermissionElements(keywordToTypeMapBuilder);
        appendConditionalElements(keywordToTypeMapBuilder);
        appendConstrainElements(keywordToTypeMapBuilder);
        appendContainerElements(keywordToTypeMapBuilder);
        appendContextElements(keywordToTypeMapBuilder);
        appendInitialSidElements(keywordToTypeMapBuilder);
        appendLabelingElements(keywordToTypeMapBuilder);
        appendMacroStatements(keywordToTypeMapBuilder);
        appendMultiLevelSecurityElements(keywordToTypeMapBuilder);
        appendRoleElements(keywordToTypeMapBuilder);
        appendTypeElements(keywordToTypeMapBuilder);
        appendTypeEnforcementRuleElements(keywordToTypeMapBuilder);
        appendUserElements(keywordToTypeMapBuilder);

        keywordToTypeMap = keywordToTypeMapBuilder.build();
    }

    private static void append(ImmutableMap.Builder<String, CilTopLevelElementType> builder,
                               IElementType type) {
        Preconditions.checkArgument(type instanceof CilTopLevelElementType,
                "Element type must be an instance " +
                        "of CilTopLevelElementType.");

        CilTopLevelElementType cilType = (CilTopLevelElementType) type;
        builder.put(cilType.getKeyword().toLowerCase(), cilType);
    }

    private static void appendClassAndPermissionElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.CLASSCOMMON_STATEMENT);
        append(builder, CilTypes.CLASS_DECLARATION);
        append(builder, CilTypes.CLASSMAP_DECLARATION);
        append(builder, CilTypes.CLASSMAPPING_STATEMENT);
        append(builder, CilTypes.CLASSORDER_STATEMENT);
        append(builder, CilTypes.CLASSPERMISSION_DECLARATION);
        append(builder, CilTypes.CLASSPERMISSIONSET_STATEMENT);
        append(builder, CilTypes.COMMON_DECLARATION);
    }

    private static void appendConditionalElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.BOOLEAN_DECLARATION);
        append(builder, CilTypes.BOOLEANIF_STATEMENT);
        append(builder, CilTypes.TUNABLE_DECLARATION);
        append(builder, CilTypes.TUNABLEIF_STATEMENT);
    }

    private static void appendConstrainElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.CONSTRAIN_STATEMENT);
        append(builder, CilTypes.MLSCONSTRAIN_STATEMENT);
    }

    private static void appendContainerElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.BLOCKABSTRACT_STATEMENT);
        append(builder, CilTypes.BLOCKINHERIT_STATEMENT);
        append(builder, CilTypes.BLOCK_DECLARATION);
        append(builder, CilTypes.IN_STATEMENT);
        append(builder, CilTypes.OPTIONAL_DECLARATION);
    }

    private static void appendContextElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.CONTEXT_DECLARATION);
    }

    private static void appendInitialSidElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.INITIALSID_DECLARATION);
        append(builder, CilTypes.SID_DECLARATION);
        append(builder, CilTypes.SIDCONTEXT_STATEMENT);
        append(builder, CilTypes.SIDORDER_STATEMENT);
    }

    private static void appendLabelingElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.FILECON_STATEMENT);
    }

    private static void appendMacroStatements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.CALL_STATEMENT);
        append(builder, CilTypes.MACRO_DECLARATION);
    }

    private static void appendMultiLevelSecurityElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.CATEGORY_DECLARATION);
        append(builder, CilTypes.CATEGORYALIAS_DECLARATION);
        append(builder, CilTypes.CATEGORYALIASACTUAL_STATEMENT);
        append(builder, CilTypes.CATEGORYORDER_STATEMENT);
        append(builder, CilTypes.LEVEL_DECLARATION);
        append(builder, CilTypes.LEVELRANGE_DECLARATION);
        append(builder, CilTypes.SENSITIVITY_DECLARATION);
        append(builder, CilTypes.SENSITIVITYALIAS_DECLARATION);
        append(builder, CilTypes.SENSITIVITYALIASACTUAL_STATEMENT);
        append(builder, CilTypes.SENSITIVITYCATEGORY_STATEMENT);
        append(builder, CilTypes.SENSITIVITYORDER_STATEMENT);
//        add("categoryset", CilStatementKind.NAMED_SET_MODIFIER, SYM_SET_MODIFIER);
    }

    private static void appendRoleElements(ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.ROLE_DECLARATION);
        append(builder, CilTypes.ROLEALLOW_STATEMENT);
        append(builder, CilTypes.ROLEATTRIBUTE_DECLARATION);
        append(builder, CilTypes.ROLEATTRIBUTESET_STATEMENT);
        append(builder, CilTypes.ROLEBOUNDS_STATEMENT);
        append(builder, CilTypes.ROLETRANSITION_STATEMENT);
        append(builder, CilTypes.ROLETYPE_STATEMENT);
    }

    private static void appendTypeElements(ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.TYPE_DECLARATION);
        append(builder, CilTypes.TYPEALIAS_DECLARATION);
        append(builder, CilTypes.TYPEALIASACTUAL_STATEMENT);
        append(builder, CilTypes.TYPEATTRIBUTE_DECLARATION);
        append(builder, CilTypes.TYPEATTRIBUTESET_STATEMENT);
        append(builder, CilTypes.TYPEBOUNDS_STATEMENT);
        append(builder, CilTypes.TYPECHANGE_STATEMENT);
        append(builder, CilTypes.TYPEMEMBER_STATEMENT);
        append(builder, CilTypes.TYPEPERMISSIVE_STATEMENT);
        append(builder, CilTypes.TYPETRANSITION_STATEMENT);
    }

    private static void appendTypeEnforcementRuleElements(
            ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.ALLOW_STATEMENT);
        append(builder, CilTypes.AUDITALLOW_STATEMENT);
        append(builder, CilTypes.DONTAUDIT_STATEMENT);
        append(builder, CilTypes.NEVERALLOW_STATEMENT);
    }

    private static void appendUserElements(ImmutableMap.Builder<String, CilTopLevelElementType> builder) {
        append(builder, CilTypes.USER_DECLARATION);
    }

    public static Optional<CilTopLevelElementType> get(String keyword) {
        return Optional.ofNullable(keywordToTypeMap.get(keyword.toLowerCase()));
    }
}
