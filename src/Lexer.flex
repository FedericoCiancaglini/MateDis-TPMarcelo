%%
%class Lexer
%type Token
L = [a-zA-Z_]
D = [0-9]
WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/*Ignore*/}
"=" {return Token.ASSIGN;}
"+" {return Token.SUMA;}
"*" {return Token.MULT;}
"-" {return Token.RESTA;}
"/" {return Token.DIV;}
{L}({L}|{D})* {lexeme=yytext(); return Token.ID;}
 ("(-"{D}+")")|{D}+ {lexeme=yytext(); return Token.INT;}
. {return Token.ERROR;}