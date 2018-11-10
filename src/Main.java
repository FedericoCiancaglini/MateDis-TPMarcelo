import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(final String[] args) throws Exception {

        /*final Stack<Symbol> symbols = new Stack<Symbol>();
        symbols.push(new Symbol(0, 0));
        symbols.push(new Symbol(0, 0));
        symbols.push(new Symbol(sym.SEMI));
        symbols.push(new Symbol(sym.NUMBER, 10));
        symbols.push(new Symbol(sym.PLUS));
        symbols.push(new Symbol(sym.NUMBER, 10));
        symbols.push(new Symbol(sym.PLUS));
        symbols.push(new Symbol(sym.NUMBER, 10));



        parser parser = new parser(new Scanner() {
            public Symbol next_token() throws Exception {
               return symbols.pop();
            }
        });*/

        parser parser = new parser(new Scanner() {
            Lexer lexer = new Lexer(new FileReader("/Users/Florencia/projects/MateDis-TPMarcelo/src/textPrueba.txt"));
            public Symbol next_token() throws Exception {
                Symbol s =  lexer.next_token();
                return s;
            }
        });
       parser.parse();
    }
}