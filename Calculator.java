import java.util.Scanner;

import src.Lexer;

public class Calculator {
    static void run(String code) {
        System.out.println(new Lexer(code).lex());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String cmd = scanner.nextLine();

            if (cmd.equals("exit"))
                break;

            run(cmd);
        }

        scanner.close();
    }
}