import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Convertor {
    // tested with 2 * 20 / 2 + ( 3 + 4 ) * 3 ^ 2 - 6 + 15
    private final static String START_CHAR = "START";
    private final static String START_OPERATOR_CHAR = START_CHAR + "_OPERATOR";
    private final static String END_CHAR = "END";
    public static Stack<String> toPostfix(List<String> pureInput) {
        ArrayList<String> inputList = new ArrayList<>(pureInput);
        inputList.addFirst(START_CHAR);
        Stack<String> Output = new Stack<>();
        Stack<String> OperatorStack = new Stack<>();
        OperatorStack.add(START_OPERATOR_CHAR);

        inputList.addLast(END_CHAR);
        String currentToken = "";
        int listIndex = 0;
        while (true) {
            currentToken = inputList.get(listIndex);

            if (isOperator(currentToken)) {
                System.out.println(currentToken + " is a operator");
            } else {
                if (isNumber(currentToken)) {
                    System.out.println(currentToken + " is a number");
                } else {
                    System.out.println(currentToken + " is not a operator or a number");
                }
            }


            if (inputList.get(listIndex).equals(END_CHAR)) {
                break;
            }
            if (isNumber(currentToken)) {
                Output.add(currentToken);
            } else if (isOperator(currentToken)) {
                    if (currentToken.equals(")")) {
                        if (!OperatorStack.peek().equals("(")) {
                            Output.push(OperatorStack.pop());
                            if (OperatorStack.peek().equals(START_OPERATOR_CHAR)) {
                                System.out.println("[ERROR]: LEFT PARENTHESIS NOT FOUND");
                                throw new RuntimeException("[ERROR]: LEFT PARENTHESIS NOT FOUND");
                            }
                            continue;
                        } else {
                            OperatorStack.pop();
                        }

                    } else {
                        if (popAndPush(currentToken, OperatorStack.peek())) {
                            Output.push(OperatorStack.pop());
                            continue;
                        } else {
                            OperatorStack.push(currentToken);
                        }
                    }
            }
            listIndex++;

        }
        for (int i = OperatorStack.size(); i > 0; i--) {
            if (OperatorStack.peek().equals(START_OPERATOR_CHAR)) {
                break;
            }
            Output.push(OperatorStack.pop());
        }
        return Output;
    }


    private static boolean popAndPush(String inputOperator, String stackOperator) {
        if (stackOperator.charAt(0) == '(' || inputOperator.charAt(0) == '(') {return false;}
        int inputPriority = getPriority(inputOperator.charAt(0));
        int stackPriority = getPriority(stackOperator.charAt(0));
        return stackPriority > inputPriority || (isLeftOrder(inputOperator) && inputPriority == stackPriority);
    }

    private static boolean isLeftOrder(String inputStr) {
        final String LEFT_ORDER = "x*/%+-";
        return LEFT_ORDER.contains(inputStr);
    }
    private static boolean isLeftOrder(String inputStr1, String inputStr2) {
        return isLeftOrder(inputStr1) && isLeftOrder(inputStr2);
    }

    private static boolean isNumber(String inputStr) {
        final String NUMBERS = "0123456789";
        String firstCharOfInputStr = inputStr.substring(0,1);
        return NUMBERS.contains(firstCharOfInputStr);
    }
    private static boolean isOperator(String inputStr) {
        final String OPERATORS = "()+-x*/%e^";
        return OPERATORS.contains(inputStr);
    }

    private static int getPriority(char Operator) {
        return switch (Operator) {
            case '(', ')' -> 1;
            case '+', '-' -> 2;
            case 'x', '*', '/', '%' -> 3;
            case 'e', '^' -> 4;
            default -> 0;
        };
    }
}
