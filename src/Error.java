package src;

public class Error extends RuntimeException {
    private static final String RED = "\u001b";
    private static final String RESET = "\033[0m";

    public Error(String name, String message) {
        System.out.println(RED + name + ": " + message + RESET);
        System.exit(1);
    }
}
