package com.codingmates.intellij.selinux.cil.lang.core.lexer;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static com.codingmates.intellij.selinux.cil.lang.core.CilTokenTypes.*;
%%

%{
  public CilLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class CilLexer
%implements FlexLexer
%function advance
%type IElementType

alpha		= [a-zA-Z]
digit		= [0-9]
true		= "true"
false       = "false"
spec_char	= [\[\]\@\=\/\*\-\_\$\%\+\-\!\|\&\^\:\~\`\#\{\}\'\<\>\?\,]
symbol		= ({digit}|{alpha}|{spec_char})+
white		= [ \t\n\r]
qstring		= \"[^\n]*\"
comment		= ;[^\n]*
dot         = \.

%%
<YYINITIAL> {
  {white}         { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "("                   { return LPAREN; }
  ")"                   { return RPAREN; }

  {dot}                 { return DOT; }
  {qstring}             { return STRING; }
  {comment}             { return SEMICOLON; }
  {true}                { return TRUE; }
  {false}               { return FALSE; }
  {symbol}              { return IDENTIFIER; }

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
