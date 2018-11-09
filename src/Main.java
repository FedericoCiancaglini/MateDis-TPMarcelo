import java.io.File;
import static jflex.Main.generate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    static List<Identificador> tokenslist;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int contIDs=0;
        tokenslist = new LinkedList<Identificador>();
        File fichero = new File ("fichero.txt");
        String path = "/Users/fede/projects/MateDis-TPMarcelo/src/Lexer.flex";
        generarLexer(path);
        String option = scanner.next();
        while (!option.equals("EOF")) {
            switch (option){
                default:
                    probarLexerFile(fichero, contIDs, tokenslist);
                    option = scanner.next();
            }
        }

    }

    public static void generarLexer(String path){
        File file=new File(path);
        generate(file);
    }

    public static void probarLexerFile(File fichero, int contIDs, List<Identificador> tokenslist) throws IOException{
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(scanner.next());
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
        String resultado="";
        while (true){
            Token token =lexer.yylex();
            if (token == null){
                for(int i=0;i<tokenslist.size();i++){
                    System.out.println(tokenslist.get(i).nombre + "=" + tokenslist.get(i).ID);
                }
                System.out.println(resultado);
                return;
            }
            switch (token){
                case SUMA:
                    resultado=resultado+ "<+>";
                    break;
                case RESTA:
                    resultado=resultado+ "<->";
                    break;
                case MULT:
                    resultado=resultado+ "<*>";
                    break;
                case DIV:
                    resultado=resultado+ "</>";
                    break;
                case ASSIGN:
                    resultado=resultado+ "<=>";
                    break;
                case ERROR:
                    resultado=resultado+ "Error, simbolo no reconocido ";
                    break;
                case ID: {
                    contIDs++;
                    Identificador tokenitem = new Identificador();
                    tokenitem.nombre = lexer.lexeme;
                    tokenitem.ID = contIDs;
                    tokenslist.add(tokenitem);
                    resultado=resultado+ "<ID" + contIDs + "> ";
                    break;
                }
                case INT:
                    resultado=resultado+ "<" + lexer.lexeme + ">";
                    break;
                default:
                    resultado=resultado+ "<"+ lexer.lexeme + "> ";
            }
        }
    }
}