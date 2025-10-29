package christmas.view;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int getVisitDate() {
        return Integer.parseInt(scanner.nextLine());
    }

    public String getOrderDetails() {
        return scanner.nextLine();
    }


}
