package christmas.view;

import christmas.util.input.InputFactory;
import christmas.util.input.IntegerReader;

import java.util.Scanner;

public class InputView {
    final Scanner sc = new Scanner(System.in);

    public String readLine() {
        return sc.nextLine();
    }

    public void close() {
        sc.close();
    }
}
