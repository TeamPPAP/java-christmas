package christmas.view;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int getVisitDate() {
        int visitDate =  Integer.parseInt(scanner.nextLine());
        System.out.println();
        return visitDate;
    }

    public String getOrderDetails() {
        String order =  scanner.nextLine();
        System.out.println();
        return order;
    }


}
