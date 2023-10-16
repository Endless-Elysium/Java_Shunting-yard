import java.util.Arrays;
import java.util.Stack;
import java.util.List;
import java.util.Scanner;

public class Main {
    final static Scanner CONSOLE_INPUT = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter:");
        String input = CONSOLE_INPUT.nextLine();
        List<String> splitInput = Arrays.stream(input.split(" ")).toList();
        Stack<String> postFix = Convertor.toPostfix(splitInput);
        for (String ch : postFix) {
            System.out.print(ch);
        }
        System.out.println();
        System.out.println(Calculator.Solve(Convertor.addEndChar(postFix)));
    }
}