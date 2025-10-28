package christmas.exception;

public class ExceptionHandler {
    private static String ERROR = "[ERROR] ";

    public static void handle(Exception e) {
        System.out.println(ERROR + e.getMessage());
    }
}
