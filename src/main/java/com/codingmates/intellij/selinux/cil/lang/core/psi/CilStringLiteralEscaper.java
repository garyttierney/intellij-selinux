package com.codingmates.intellij.selinux.cil.lang.core.psi;

import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilStringExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import org.jetbrains.annotations.NotNull;

public class CilStringLiteralEscaper extends LiteralTextEscaper<CilStringExpression> {

    public CilStringLiteralEscaper(@NotNull CilStringExpression host) {
        super(host);
    }

    @Override
    public boolean decode(@NotNull final TextRange rangeInsideHost,
                          @NotNull final StringBuilder outChars) {
        outChars.append(myHost.getText(), rangeInsideHost.getStartOffset(),
                rangeInsideHost.getEndOffset());
        return true;
    }

    @Override
    public int getOffsetInHost(final int offsetInDecoded,
                               @NotNull final TextRange rangeInsideHost) {
        int offset = offsetInDecoded + rangeInsideHost.getStartOffset();
        if (offset < rangeInsideHost.getStartOffset()) {
            offset = rangeInsideHost.getStartOffset();
        }
        if (offset > rangeInsideHost.getEndOffset()) {
            offset = rangeInsideHost.getEndOffset();
        }
        return offset;
    }

    @Override
    public boolean isOneLine() {
        return true;
    }
}
