package com.codingmates.intellij.selinux.cil.lang.core.psi.api;

/**
 * A
 *
 * @param <R> The expected return type of the visitor.
 */
public interface CilExpressionVisitor<R> {

    R visit(CilExpression expression);

    R visit(CilReferenceExpression referenceExpression);

    R visit(CilListExpression listExpression);

    R visit(CilLevelRangeExpression levelRange);

    R visit(CilNamedListExpression symbolAndListExpression);

    R visit(CilBinaryExpression binaryExpression);

    R visit(CilUnaryExpression unaryExpression);
}
