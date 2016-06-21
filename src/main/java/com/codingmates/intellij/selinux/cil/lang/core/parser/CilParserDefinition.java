package com.codingmates.intellij.selinux.cil.lang.core.parser;

import com.codingmates.intellij.selinux.cil.lang.core.CilElementFactory;
import com.codingmates.intellij.selinux.cil.lang.core.CilTokenType;
import com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes;
import com.codingmates.intellij.selinux.cil.lang.core.lexer.CilLexerAdapter;
import com.codingmates.intellij.selinux.cil.lang.core.psi.api.CilFile;
import com.codingmates.intellij.selinux.cil.lang.core.stubs.types.CilFileStubElementType;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class CilParserDefinition implements ParserDefinition {

    public static final TokenSet COMMENTS = TokenSet.create(CilTokenTypes.SEMICOLON);
    public static final TokenSet STRING_LITERALS = TokenSet.create(CilTokenTypes.STRING);
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);

    @NotNull
    @Override
    public PsiElement createElement(ASTNode astNode) {
        IElementType elementType = astNode.getElementType();

        if (elementType instanceof CilTokenType) {
            return new LeafPsiElement(elementType, astNode.getText());
        } else if (elementType instanceof CilElementFactory) {
            CilElementFactory type = (CilElementFactory) elementType;
            return type.createElement(astNode);
        } else {
            throw new IllegalArgumentException(
                    "ASTNode must represent a CilCompositeElementFactory or CilTokenType");
        }
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new CilFile(fileViewProvider);
    }

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new CilLexerAdapter();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new CilParser();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @Override
    public IFileElementType getFileNodeType() {
        return CilFileStubElementType.INSTANCE;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return STRING_LITERALS;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode1) {
        return SpaceRequirements.MAY;
    }
}
