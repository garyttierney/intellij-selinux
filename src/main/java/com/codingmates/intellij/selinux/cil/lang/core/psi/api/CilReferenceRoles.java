package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

import com.intellij.psi.tree.IElementType;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTypes.*;

public final class CilReferenceRoles {
    private CilReferenceRoles() {

    }

    public static final CilReferenceRole ACCESS_VECTOR_REFERENCE = new CilReferenceRole(CLASS_DECLARATION, CLASSMAP_DECLARATION, CLASSPERMISSION_DECLARATION);
    public static final CilReferenceRole CATEGORYALIAS_REFERENCE = new CilReferenceRole(CATEGORYALIAS_DECLARATION);
    public static final CilReferenceRole CATEGORY_OR_ALIAS_REFERENCE = new CilReferenceRole(CATEGORY_DECLARATION, CATEGORYALIAS_DECLARATION);
    public static final CilReferenceRole CATEGORY_REFERENCE = new CilReferenceRole(CATEGORY_DECLARATION);
    public static final CilReferenceRole CLASS_REFERENCE = new CilReferenceRole(CLASS_DECLARATION);
    public static final CilReferenceRole COMMON_REFERENCE = new CilReferenceRole(COMMON_DECLARATION);
    public static final CilReferenceRole BLOCK_REFERENCE = new CilReferenceRole(BLOCK_DECLARATION);
    public static final CilReferenceRole CONTAINER_REFERENCE = new CilReferenceRole(BLOCK_DECLARATION, MACRO_DECLARATION, OPTIONAL_DECLARATION);
    public static final CilReferenceRole CONTEXT_REFERENCE = new CilReferenceRole(CONTEXT_DECLARATION);
    public static final CilReferenceRole MACRO_REFERENCE = new CilReferenceRole(MACRO_DECLARATION);
    public static final CilReferenceRole ROLE_REFERENCE = new CilReferenceRole(ROLE_DECLARATION);
    public static final CilReferenceRole ROLE_SET_REFERENCE = new CilReferenceRole(ROLE_DECLARATION, ROLEATTRIBUTE_DECLARATION);
    public static final CilReferenceRole SENSITIVITY_OR_ALIAS_REFERENCE = new CilReferenceRole(SENSITIVITY_DECLARATION, SENSITIVITYALIAS_DECLARATION);
    public static final CilReferenceRole SENSITIVITY_REFERENCE = new CilReferenceRole(SENSITIVITY_DECLARATION);
    public static final CilReferenceRole TYPE_OR_ALIAS_REFERENCE = new CilReferenceRole(TYPE_DECLARATION, TYPEALIAS_DECLARATION);
    public static final CilReferenceRole TYPE_SET_REFERENCE = new CilReferenceRole(TYPE_DECLARATION, TYPEALIAS_DECLARATION, TYPEATTRIBUTE_DECLARATION);

    public static CilReferenceRole declarationsLike(IElementType type) {
        if (type == TYPEATTRIBUTE_DECLARATION) {
            return TYPE_SET_REFERENCE;
        } else if (type == ROLEATTRIBUTE_DECLARATION) {
            return ROLE_SET_REFERENCE;
        } else if (type == CLASSPERMISSION_DECLARATION) {
            return ACCESS_VECTOR_REFERENCE;
        } else {
            throw new IllegalArgumentException("Invalid argument");
        }
    }
}
