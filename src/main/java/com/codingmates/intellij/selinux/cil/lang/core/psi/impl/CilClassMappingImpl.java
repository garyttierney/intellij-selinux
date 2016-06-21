package com.codingmates.intellij.selinux.cil.lang.core.psi.impl;

import com.codingmates.intellij.selinux.cil.lang.core.CilTypes;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilClassMapping;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceExpression;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRole;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElement;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.types.CilCompositeElementBase;
import com.codingmates.intellij.selinux.cil.lang.core.psi.utils.CilPsiUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.codingmates.intellij.selinux.cil.lang.core.CilTopLevelElementTypeMap.*;
import static com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilReferenceRoles.ACCESS_VECTOR_DECLARATIONS;

public class CilClassMappingImpl extends CilCompositeElementBase implements CilClassMapping {

    public static final int ACCESS_VECTOR_OFFSET = 2;
    public static final int CLASSMAP_CHILD_OFFSET = 1;
    public static final int CLASSMAP_OFFSET = 0;

    public CilClassMappingImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public CilCompositeElement getAccessVectorExpression() {
        return CilPsiUtils.getChildAt(this, ACCESS_VECTOR_OFFSET);
    }

    @Override
    public CilReferenceRole getChildRole(IElementType childType, int childOffset) {
        switch (childOffset) {
            case CLASSMAP_OFFSET:
                return CilReferenceRole.match(CLASSMAP_DECLARATION);
            case CLASSMAP_CHILD_OFFSET:
                return CilReferenceRole.match(CLASS_DECLARATION); //@todo - fix this
            case ACCESS_VECTOR_OFFSET:
                return childType == CilTypes.REFERENCE_EXPR ?
                        CilReferenceRole.match(CLASSPERMISSION_DECLARATION) :
                        ACCESS_VECTOR_DECLARATIONS;
            default:
                throw new IllegalArgumentException("Invalid child offset");
        }
    }

    @Override
    public CilReferenceExpression getClassMapChildReference() {
        return CilPsiUtils.getChildAt(this, CLASSMAP_CHILD_OFFSET);
    }

    @Override
    public CilReferenceExpression getClassMapReference() {
        return CilPsiUtils.getChildAt(this, CLASSMAP_OFFSET);
    }
}
