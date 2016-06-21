package com.codingmates.intellij.selinux.cil.lang.core;

public enum CilStatementParseHint {
    NAMED_CONTAINER,
    NAME_DECLARATION,
    /**
     * A statement which takes a single symbol as an argument.  Similar to {@link
     * #NAME_DECLARATION}, but has a reference as an argument instead of an identifier.
     */
    NAME_MODIFIER,
    NAME_ASSOCIATION,
    /**
     * A statement which consists of a symbol, associated with a list containing a symbol and list
     * of expressions.
     */
    SYMBOL_LIST_EXPR_ASSOCIATION,
    NAME_ORDERING,
    NAMED_LIST_DECLARATION,
    NAMED_SET_MODIFIER,
    TYPE_ENFORCEMENT_RULE,
    CONSTRAIN,
    TUNABLE_IF,
    CLASS_MAPPING,
    CALL,
    MACRO_DECLARATION,
    CONTEXT_DECLARATION,
    PORT_CONTEXT,
    NODE_CONTEXT,
    NETIF_CONTEXT,
    FILE_CONTEXT,
    SID_CONTEXT,
    POLICY_CONFIG,
    TRANSITION_RULE,
    LEVEL_RANGE_DECLARATION,
    LEVEL_DECLARATION,
    TUNABLE_DECLARATION
}
