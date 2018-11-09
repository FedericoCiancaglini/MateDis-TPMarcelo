import java.util.Scanner;

public class Utils {

    public static Object operate(Object v1, Object o, Object v2) {
        try {
        char operator = ((String) o).charAt(0);
        if(v1 instanceof Boolean && v2 instanceof Boolean){
            switch (operator) {
                case '&':
                    return (Boolean)v1 && (Boolean)v2;
                case '|':
                    return (Boolean)v1 || (Boolean)v2;
            }
        }

        if (v1 instanceof String && v2 instanceof String){
            if (o.equals("+")) return v1 + (String)v2;

        }

        if (v1 instanceof Integer && v2 instanceof Integer){
            switch (operator){
                case '+':
                    return (Integer)v1 + (Integer)v2;
                case '-':
                    return (Integer)v1 - (Integer)v2;
                case '/':
                    return (Integer)v1 / (Integer)v2;
                case '*':
                    return (Integer)v1 * (Integer)v2;
                case '%':
                    return (Integer)v1 % (Integer)v2;
                case '>':
                    return (Integer)v1 > (Integer)v2;
                case '<':
                    return (Integer)v1 < (Integer)v2;
                case '!':
                    return !v1.equals(v2);
                case '=':
                    return v1.equals(v2);
            }
        }

        if (v1 instanceof Float && v2 instanceof Float){
            switch (operator){
                case '+':
                    return (Float)v1 + (Float)v2;
                case '-':
                    return (Float)v1 - (Float)v2;
                case '/':
                    return (Float)v1 / (Float)v2;
                case '*':
                    return (Float)v1 + (Float)v2;
                case '%':
                    return (Float)v1 % (Float)v2;
                case '>':
                    return (Float)v1 > (Float)v2;
                case '<':
                    return (Float)v1 < (Float)v2;
            }
        }}catch (Exception e) {
            throw new SyntaxError("Operate: " + v1 + " - " + o + " - " + v2);
        }
        throw new SyntaxError("Operate: " + v1 + " - " + o + " - " + v2);
    }

    public static Object parseRead(Object type) throws SyntaxError {
        System.out.println("enter " + type + ": ");
        try {
            if (type.equals("int")) return Integer.parseInt(new Scanner(System.in).nextLine().split(" ")[0]);
            if (type.equals("string")) return new Scanner(System.in).nextLine().split(" ")[0];
            if (type.equals("float")) return Float.parseFloat(new Scanner(System.in).nextLine().split(" ")[0]);
            if (type.equals("boolean")) return Boolean.valueOf(new Scanner(System.in).nextLine().split(" ")[0]);
            throw new SyntaxError("read: " + type);
        }catch (Exception e){
            throw new SyntaxError("read: " + type);
        }
    }

}
