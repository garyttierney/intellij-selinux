package com.codingmates.intellij.selinux.cil.lang.core;


import com.codingmates.intellij.selinux.cil.lang.core.psi.CilNameAssociationElementType;
import com.codingmates.intellij.selinux.cil.lang.core.psi.CilNameOrderingElementType;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.impl.*;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.*;

public class CilTypes {

    /**
     * A binary expression with 2 operands.
     */
    public static final IElementType BINARY_EXPR = new CilCompositeElementType("binary_expr",
            CilBinaryExpressionImpl::new);

    /**
     * An argument list of a {@code call} statement.
     */
    public static final IElementType CALL_ARG_LIST = new CilCompositeElementType("<argument list>",
            CilCallArgumentListImpl::new);

    /**
     * An anonymous security context expression.
     */
    public static final IElementType CONTEXT_EXPR = new CilCompositeElementType("context_expr",
            CilContextExpressionImpl::new);

    /**
     * An anonymous MLS range expression.
     */
    public static final IElementType LEVEL_RANGE_EXPR = new CilCompositeElementType(
            "level_range_expr", CilLevelRangeExpressionImpl::new);

    /**
     * An expression representing a list of symbol references.
     */
    public static final IElementType LIST_EXPR = new CilCompositeElementType("list_expr",
            CilListExpressionImpl::new);

    /**
     * A declaration of a macro parameter.
     */
    public static final IElementType MACRO_PARAMETER_DECL = new CilCompositeElementType("macro_argument",
            CilMacroParameterImpl::new);

    /**
     * A list item declaration.
     */
    public static final IElementType NAMED_LIST_ITEM_DECLARATION = new CilNamedListItemDeclarationStubElementType(
            "<named list item>");

    /**
     * A reference to a previously declared symbol.
     */
    public static final IElementType REFERENCE_EXPR = new CilCompositeElementType("reference_expr",
            CilReferenceExpressionImpl::new);

    /**
     * A string literal expression.
     */
    public static final IElementType STRING_EXPRESSION = new CilCompositeElementType(
            "<string expression>", CilStringExpressionImpl::new);

    /**
     * A tuple of a symbol and list, often a class and permission list.
     */
    public static final IElementType SYMBOL_AND_LIST_EXPR = new CilCompositeElementType(
            "class_permission_expr", CilNamedListExpressionImpl::new);

    /**
     * A branch in a tunable/boolean if statement.
     */
    public static final IElementType TUNABLE_IF_BRANCH = new CilCompositeElementType(
            "<tunable conditional branch>", CilTunableIfBranchImpl::new);

    /**
     * A unary expression with a single operand.
     */
    public static final IElementType UNARY_EXPR = new CilCompositeElementType("<unary expression>",
            CilUnaryExpressionImpl::new);
    /**
     * A type enforcement rule which grants the source type the given permissions on the target
     * type.
     */
    public static final IElementType ALLOW_STATEMENT = typeEnforcementRule("allow");
    /**
     * A type enforcement rule which causes an audit record to be logged when a given set of
     * permissions is granted to a source type on a given target type.
     */
    public static final IElementType AUDITALLOW_STATEMENT = typeEnforcementRule("auditallow");
    /**
     * A statement which declares its argument as abstract (able to be used as an argument in {@link
     * #BLOCKINHERIT_STATEMENT}s).
     */
    public static final IElementType BLOCKABSTRACT_STATEMENT = nameModifier("blockabstract");
    /**
     * A statement which copies the abstract {@link #BLOCK_DECLARATION} referenced in its argument
     * to the current scope.
     */
    public static final IElementType BLOCKINHERIT_STATEMENT = new CilStatementElementType(
            "blockinherit",
            CilStatementParseHint.NAME_MODIFIER, CilNameModifierImpl::new);
    /**
     * A declaration of a named block container.
     */
    public static final IElementType BLOCK_DECLARATION = new CilBlockStubElementType("block");
    /**
     * A conditional statement with an expression containing policy booleans.
     */
    public static final IElementType BOOLEANIF_STATEMENT = new CilStatementElementType("booleanif",
            CilStatementParseHint.TUNABLE_IF, CilTunableIfImpl::new);
    /**
     * A declaration of a policy boolean.
     */
    public static final IElementType BOOLEAN_DECLARATION = new CilStatementElementType("boolean",
            CilStatementParseHint.TUNABLE_DECLARATION, CilTunableDeclarationImpl::new);
    /**
     * A parameterized macro call statement.
     */
    public static final IElementType CALL_STATEMENT = new CilStatementElementType("call",
            CilStatementParseHint.CALL, CilCallStatementImpl::new);
    /**
     * A declaration of a new category alias.
     */
    public static final IElementType CATEGORYALIAS_DECLARATION = nameDeclaration("categoryalias");
    /**
     * A declaration of a new category.
     */
    public static final IElementType CATEGORY_DECLARATION = nameDeclaration("category");
    /**
     * Defines the ordering for declared categories.
     */
    public static final IElementType CATEGORYORDER_STATEMENT = nameOrdering("categoryorder",
            () -> CATEGORY_REFERENCE);
    /**
     * Associate a categoryalias with an actual category.
     */
    public static final IElementType CATEGORYALIASACTUAL_STATEMENT = nameAssociation("categoryaliasactual",
            () -> CATEGORYALIAS_REFERENCE, () -> CATEGORY_REFERENCE);
    /**
     * Map a classmap list item to a collection of another classes permissions.
     */
    public static final IElementType CLASSMAPPING_STATEMENT = new CilStatementElementType(
            "classmapping",
            CilStatementParseHint.CLASS_MAPPING, CilClassMappingImpl::new);
    /**
     * A declaration of a {@code classmap}, which maps pseduo-security classes to collections of
     * permissions.
     */
    public static final IElementType CLASSMAP_DECLARATION = namedListDeclaration("classmap");
    /**
     * A named set modifier which modifies the access vectors in a {@link
     * #CLASSPERMISSION_DECLARATION},
     */
    public static final IElementType CLASSPERMISSIONSET_STATEMENT = namedSetModifier(
            "classpermissionset");
    /**
     * Declare a new classpermission (a named set of access vectors).
     */
    public static final IElementType CLASSPERMISSION_DECLARATION = nameDeclaration(
            "classpermission");
    /**
     * A declaration of a security class and its permissions.
     */
    public static final IElementType CLASS_DECLARATION = namedListDeclaration("class");
    /**
     * A statement which defines the ordering of classes.
     */
    public static final IElementType CLASSORDER_STATEMENT = nameOrdering("classorder",
            () -> CLASS_REFERENCE);
    /**
     * A declaration of a common parent for {@link #CLASS_DECLARATION}s.
     */
    public static final IElementType COMMON_DECLARATION = namedListDeclaration("common");
    /**
     * Associate a {@link #CLASS_DECLARATION} with a {@link #COMMON_DECLARATION} and inherit the
     * common permissions.
     */
    public static final IElementType CLASSCOMMON_STATEMENT = nameAssociation("classcommon",
            () -> CLASS_REFERENCE, () -> COMMON_REFERENCE);
    /**
     * Constrain a set of permissions based on an arbitrary expression which evaluates the source
     * and target contexts.
     */
    public static final IElementType CONSTRAIN_STATEMENT = new CilStatementElementType("constrain",
            CilStatementParseHint.CONSTRAIN, CilConstrainImpl::new);
    /**
     * A named security context declaration.
     */
    public static final IElementType CONTEXT_DECLARATION = new CilStatementElementType("context",
            CilStatementParseHint.CONTEXT_DECLARATION, CilContextDeclarationImpl::new);
    /**
     * A type enforcement rule which prevents audit records from being logged when a source type is
     * denied a given set of permissions on a target type.
     */
    public static final IElementType DONTAUDIT_STATEMENT = typeEnforcementRule("dontaudit");
    /**
     * A file context specification which takes a named or anonymous context as its argument.
     */
    public static final IElementType FILECON_STATEMENT = new CilStatementElementType("filecon",
            CilStatementParseHint.FILE_CONTEXT, CilFileContextImpl::new);
    /**
     * Declares a new kernel initial security identifier.
     */
    public static final IElementType INITIALSID_DECLARATION = nameDeclaration("initialsid");
    /**
     * A container statement which can insert statements into the {@link #BLOCK_DECLARATION}, {@link
     * #OPTIONAL_DECLARATION}, or {@link #MACRO_DECLARATION}.
     */
    public static final IElementType IN_STATEMENT = new CilInScopeStubElementType("in", CilInScopeImpl::new);
    /**
     * Declare a new security level range with a level range expression..
     */
    public static final IElementType LEVELRANGE_DECLARATION = new CilStatementElementType(
            "levelrange",
            CilStatementParseHint.LEVEL_RANGE_DECLARATION, CilLevelRangeDeclarationImpl::new);
    /**
     * Declare a new security level.
     */
    public static final IElementType LEVEL_DECLARATION = nameDeclaration("level");
    /**
     * A declaration of a container with parameters which can be called as a function.
     */
    public static final IElementType MACRO_DECLARATION = new CilMacroDeclarationStubElementType(
            "macro");
    /**
     * A constrain statement with the added ability of using security level and dominance
     * expressions.
     */
    public static final IElementType MLSCONSTRAIN_STATEMENT = new CilStatementElementType(
            "mlsconstrain",
            CilStatementParseHint.CONSTRAIN, CilConstrainImpl::new);
    /**
     * A compile-time type enforcement rule used to verify that no other rules violate the policy
     * model.
     */
    public static final IElementType NEVERALLOW_STATEMENT = typeEnforcementRule("neverallow");
    /**
     * A declaration of a named optional container.
     */
    public static final IElementType OPTIONAL_DECLARATION = new CilStatementElementType(
            "<optional>",
            CilStatementParseHint.NAMED_CONTAINER, CilOptionalDeclarationImpl::new);
    /**
     * A named set modifier which targets {@link #ROLE_DECLARATION} and {@link
     * #ROLEATTRIBUTE_DECLARATION}.
     */
    public static final IElementType ROLEATTRIBUTESET_STATEMENT = namedSetModifier(
            "roleattributeset");
    /**
     * A declaration of a named role set.
     */
    public static final IElementType ROLEATTRIBUTE_DECLARATION = nameDeclaration("roleattribute");
    /**
     * Create a transition rule which transitions roles (must also be authorized by {@link
     * #ROLEALLOW_STATEMENT}).
     */
    public static final IElementType ROLETRANSITION_STATEMENT = transition("roletransition");
    /**
     * A declaration of a named role.
     */
    public static final IElementType ROLE_DECLARATION = nameDeclaration("role");
    /**
     * A statement which authorizes a role to change to another role.
     */
    public static final IElementType ROLEALLOW_STATEMENT = nameAssociation("roleallow",
            () -> ROLE_SET_REFERENCE, () -> ROLE_SET_REFERENCE);
    /**
     * Create bounds between two roles.
     */
    public static final IElementType ROLEBOUNDS_STATEMENT = nameAssociation("rolebounds",
            () -> ROLE_REFERENCE, () -> ROLE_REFERENCE);
    /**
     * Associate a role with the given type.
     */
    public static final IElementType ROLETYPE_STATEMENT = nameAssociation("roletype",
            () -> ROLE_SET_REFERENCE, () -> TYPE_OR_ALIAS_REFERENCE);
    /**
     * Declare a new sensitivity alias.
     */
    public static final IElementType SENSITIVITYALIAS_DECLARATION = nameDeclaration(
            "sensitivityalias");

    /**
     * A statement that associates categories with a previously declared sensitivity level.
     */
    public static final IElementType SENSITIVITYCATEGORY_STATEMENT = nameAssociation("sensitivitycategory",
            () -> SENSITIVITY_OR_ALIAS_REFERENCE, () -> CATEGORY_OR_ALIAS_REFERENCE);
    /**
     * Declare a new sensitivity level.
     */
    public static final IElementType SENSITIVITY_DECLARATION = nameDeclaration("sensitivity");

    /**
     * Associate a sensitivityalias with an actual sensitivity identifier.
     */
    public static final IElementType SENSITIVITYALIASACTUAL_STATEMENT = nameAssociation("sensitivityaliasactual",
            () -> CilReferenceRole.match(SENSITIVITYALIAS_DECLARATION), () -> SENSITIVITY_REFERENCE);

    /**
     * Defines the order of sensitivity levels.
     */
    public static final IElementType SENSITIVITYORDER_STATEMENT = nameOrdering("sensitivityorder",
            () -> SENSITIVITY_REFERENCE);
    /**
     * Associate a context with a security identifier.
     */
    public static final IElementType SIDCONTEXT_STATEMENT = new CilStatementElementType(
            "sidcontext",
            CilStatementParseHint.SID_CONTEXT, CilSidContextImpl::new);
    /**
     * A declaration of a new security identifier.
     */
    public static final IElementType SID_DECLARATION = nameDeclaration("sid");
    /**
     * Define the ordering of security identifiers.
     */
    public static final IElementType SIDORDER_STATEMENT = nameOrdering("sidorder",
            () -> CilReferenceRole.match(SID_DECLARATION));
    /**
     * A conditional statement which targets compile-time tunables.
     */
    public static final IElementType TUNABLEIF_STATEMENT = new CilStatementElementType("tunableif",
            CilStatementParseHint.TUNABLE_IF, CilTunableIfImpl::new);
    /**
     * A declaration of a compile-time tunable.
     */
    public static final IElementType TUNABLE_DECLARATION = new CilStatementElementType("tunable",
            CilStatementParseHint.TUNABLE_DECLARATION, CilTunableDeclarationImpl::new);
    /**
     * A named typealias declaration.
     */
    public static final IElementType TYPEALIAS_DECLARATION = nameDeclaration("typealias");
    /**
     * A named set modifier which targets {@link #TYPE_DECLARATION}, {@link
     * #TYPEATTRIBUTE_DECLARATION}, and {@link #TYPEALIAS_DECLARATION}s.
     */
    public static final IElementType TYPEATTRIBUTESET_STATEMENT = namedSetModifier(
            "typeattributeset");
    /**
     * A named set declaration as a type attribute.
     */
    public static final IElementType TYPEATTRIBUTE_DECLARATION = nameDeclaration("typeattribute");
    /**
     * Creates bounds between two {@link #TYPE_DECLARATION}, {@link #TYPEATTRIBUTE_DECLARATION}, or
     * {@link #TYPEALIAS_DECLARATION}s
     */
    public static final IElementType TYPEBOUNDS_STATEMENT = nameAssociation("typebounds",
            () -> TYPE_OR_ALIAS_REFERENCE, () -> TYPE_OR_ALIAS_REFERENCE);
    /**
     * A transition rule which allows object managers to compute a type change for objects.
     */
    public static final IElementType TYPECHANGE_STATEMENT = transition("typechange");
    /**
     * A transition rule for poly-instantiated objects.
     */
    public static final IElementType TYPEMEMBER_STATEMENT = transition("typemember");
    /**
     * A statement which sets a type as permissive.
     */
    public static final IElementType TYPEPERMISSIVE_STATEMENT = nameModifier("typepermissive");
    /**
     * A transition rule for automatic type transitions.
     */
    public static final IElementType TYPETRANSITION_STATEMENT = transition("typetransition");
    /**
     * A named type declaration.
     */
    public static final IElementType TYPE_DECLARATION = nameDeclaration("type");
    /**
     * Associates a typealias name with an actual type id (opposed to typealias which declares a
     * typealias).
     */
    public static final IElementType TYPEALIASACTUAL_STATEMENT = nameAssociation("typealiasactual",
            () -> CilReferenceRole.match(TYPEALIAS_DECLARATION), () -> CilReferenceRole.match(TYPE_DECLARATION));
    /**
     * A named SELinux user declaration.
     */
    public static final IElementType USER_DECLARATION = nameDeclaration("user");

    private static IElementType nameAssociation(String keyword, Supplier<CilReferenceRole> sourceRoleSupplier,
                                                Supplier<CilReferenceRole> targetRoleSupplier) {
        return new CilNameAssociationElementType(keyword, CilStatementParseHint.NAME_ASSOCIATION,
                CilNameAssociationImpl::new, sourceRoleSupplier, targetRoleSupplier);
    }

    private static IElementType nameDeclaration(String typeattribute) {
        return new CilNameDeclarationStubElementType(
                typeattribute);
    }

    private static IElementType nameModifier(String keyword) {
        return new CilStatementElementType(keyword, CilStatementParseHint.NAME_MODIFIER,
                CilNameModifierImpl::new);
    }

    @NotNull
    private static IElementType nameOrdering(String sensitivityorder, Supplier<CilReferenceRole> role) {
        return new CilNameOrderingElementType(sensitivityorder, CilStatementParseHint.NAME_ORDERING,
                CilNameOrderingImpl::new, role);
    }

    private static IElementType namedListDeclaration(String keyword) {
        return new CilNamedListDeclarationStubElementType(keyword);
    }

    private static IElementType namedSetModifier(String keyword) {
        return new CilStatementElementType(keyword, CilStatementParseHint.NAMED_SET_MODIFIER,
                CilNamedSetModifierImpl::new);
    }

    private static IElementType transition(String keyword) {
        return new CilStatementElementType(keyword, CilStatementParseHint.TRANSITION_RULE,
                CilTransitionRuleImpl::new);
    }

    private static IElementType typeEnforcementRule(String keyword) {
        return new CilStatementElementType(keyword, CilStatementParseHint.TYPE_ENFORCEMENT_RULE,
                CilTypeEnforcementRuleImpl::new);
    }

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
