import java_cup.runtime.*;
%%

%class Lexer
%line
%column
%cup

%{

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

    StringBuilder stringBuilder;
%}

Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

InputCharacter = [^\r\n]

LineTerminator = \r|\n|\r\n

BlankSpace = \r|\n|\r\n|" "

Number = 0 | [1-9][0-9]*

Float = [0-9]*\.[0-9]*

Boolean = "true" | "false"

Operator = "+" | "-" | "/" | "*" | "and" | "or" | ">" | "<"

Id = [A-Za-z_][A-Za-z_0-9]*

Type = "int" | "float" | "string" | "boolean"

%state STRING
%%

<YYINITIAL> {

    "read" {return new Symbol(sym.READ_CALL);}
    "write" {return new Symbol(sym.WRITE_CALL);}
    "and"   {return new Symbol(sym.OPERATOR, "&");}
    "or"    {return new Symbol(sym.OPERATOR, "|");}
    "=="    {return new Symbol(sym.OPERATOR, "=");}
    "!="    {return new Symbol(sym.OPERATOR, "!");}
    "("     {return new Symbol(sym.OPAR);}
    ")"     {return new Symbol(sym.CPAR);}
    "="     {return new Symbol(sym.EQUALS);}
    "\""     {stringBuilder = new StringBuilder(); yybegin(STRING);}
    {Type}  {return new Symbol(sym.TYPE, yytext());}
    {Number} {return new Symbol(sym.NUMBER, Integer.parseInt(yytext()));}
    {Float}  {return new Symbol(sym.FLOAT, Float.parseFloat(yytext()));}
    {Boolean}  {return new Symbol(sym.BOOLEAN, Boolean.valueOf(yytext()));}
    {Operator} {return new Symbol(sym.OPERATOR, yytext());}
    {Id}    {return new Symbol(sym.ID, yytext());}
    {Comment}   { yytext(); }
    {BlankSpace} {}

}

<STRING>{
    "\"" {yybegin(0); return new Symbol(sym.STRING, stringBuilder.toString());}
    .    {stringBuilder.append(yytext());}
}

/* No token was found for the input so through an error.  Print out an
   Illegal character message with the illegal character that was found. */
[^]                    { throw new Error("Illegal character ["+yytext()+"]"); }