package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.intellij.psi.tree.IElementType;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap.*;

public final class CilReferenceRoles {

    public static final CilReferenceRole ACCESS_VECTOR_DECLARATIONS = type ->
            type == CLASS_DECLARATION ||
                    type == CLASSMAP_DECLARATION || type == CLASSPERMISSION_DECLARATION;
    public static final CilReferenceRole CATEGORYALIAS_REFERENCE = type -> type
            == CATEGORYALIAS_DECLARATION;
    public static final CilReferenceRole CATEGORY_DECLARATIONS = type ->
            type == CATEGORY_DECLARATION ||
                    type == CATEGORYALIAS_DECLARATION;
    public static final CilReferenceRole CATEGORY_REFERENCE = type -> type == CATEGORY_DECLARATION;
    public static final CilReferenceRole CLASS_REFERENCE = type -> type == CLASS_DECLARATION;
    public static final CilReferenceRole COMMON_REFERENCE = type -> type == COMMON_DECLARATION;
    public static final CilReferenceRole BLOCK_DECLARATION_REF = type -> type == BLOCK_DECLARATION;
    public static final CilReferenceRole CONTAINER_DECLARATIONS = type -> type == BLOCK_DECLARATION ||
            type == MACRO_DECLARATION || type == OPTIONAL_DECLARATION;
    public static final CilReferenceRole CONTEXT_REFERENCE = type -> type == CONTEXT_DECLARATION;
    public static final CilReferenceRole MACRO_REFERENCE = type -> type == MACRO_DECLARATION;
    public static final CilReferenceRole ROLE_REFERENCE = type -> type == ROLE_DECLARATION;
    public static final CilReferenceRole ROLE_SET_DECLARATIONS = type -> type == ROLE_DECLARATION ||
            type == ROLEATTRIBUTE_DECLARATION;
    public static final CilReferenceRole SENSITIVITY_DECLARATIONS = type ->
            type == SENSITIVITY_DECLARATION ||
                    type == SENSITIVITYALIAS_DECLARATION;
    public static final CilReferenceRole SENSITIVITY_REFERENCE = type -> type
            == SENSITIVITY_DECLARATION;
    public static final CilReferenceRole TYPE_DECLARATIONS = type -> type == TYPE_DECLARATION ||
            type == TYPEALIAS_DECLARATION;
    public static final CilReferenceRole TYPE_SET_DECLARATIONS = type -> type == TYPE_DECLARATION ||
            type == TYPEALIAS_DECLARATION || type == TYPEATTRIBUTE_DECLARATION;

    public static CilReferenceRole declarationsLike(IElementType type) {
        if (type == TYPEATTRIBUTE_DECLARATION) {
            return TYPE_SET_DECLARATIONS;
        } else if (type == ROLEATTRIBUTE_DECLARATION) {
            return ROLE_SET_DECLARATIONS;
        } else if (type == CLASSPERMISSION_DECLARATION) {
            return ACCESS_VECTOR_DECLARATIONS;
        } else {
            throw new IllegalArgumentException("Invalid argument");
        }
    }
}
