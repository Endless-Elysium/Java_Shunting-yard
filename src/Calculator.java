import java.util.Stack;
import java.math.*;
public class Calculator {

    private final static String END_CHAR = "END";
    static double Solve(Stack<String> Input) {
        Stack<String> numberStack = new Stack<>();
        final double Output;
        String currentOperator;
        String currentToken;
        while (true) {
            currentToken = Input.peek();
            if (currentToken.equals(END_CHAR)) {
                System.out.println("END_CHAR REACHED");
                break;
            }
            if (Convertor.isNumber(currentToken)) {
                numberStack.push(Input.pop());
            } else if(Convertor.isOperator(currentToken)) {
                currentOperator = Input.pop();
                double y = Double.parseDouble(numberStack.pop());
                double x = Double.parseDouble(numberStack.pop());
                double result = switch(currentOperator) {
                    case "+" -> add(x,y);
                    case "-" -> sub(x,y);
                    case "*","x" -> multi(x,y);
                    case "/" -> div(x,y);
                    case "%" -> mod(x,y);
                    case "e","^" -> Math.pow(x,y);
                    default -> 0;
                };
                numberStack.push(String.valueOf(result));
            }
        }


        Output = Double.parseDouble(numberStack.pop());
        return Output;
    }


    static double add(double x, double y) {return x + y;}
    static double sub(double x, double y) {return x - y;}
    static double multi(double x, double y) {return x * y;}
    static double div(double x, double y) {
        if (x == 0.0 || y == 0.0) {
            return 0.0;
        } else {
            return x / y;
        }
    }
    static double mod(double x, double y) {
        if (x == 0.0 || y == 0.0) {
            return 0.0;
        } else {
            return x % y;
        }
    }
}
