package christmas.io;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ExceptionHandler;

import java.time.LocalDate;
import java.util.List;

import static christmas.util.ObjectUtil.isBlank;
import static christmas.util.ObjectUtil.isDigit;

public class InputView {

    public LocalDate readDate() {
        System.out.println("안녕하세요! 포텐업 식당 12월 이벤트 플래너입니다.");
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                isValidAnswer(input);
                isNotDigitThanThrow(input);

                return LocalDate.parse("2025-12-" + String.format("%02d", Integer.parseInt(input)));
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
    }

    public List<String> readMenu() {
        while (true) {
            try {
                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String input = Console.readLine();

                isValidAnswer(input);
                isRightOrderFormat(input);
                return List.of(input.split(","));
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
    }

    private void isValidAnswer(String input) {
        if (isBlank(input)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private void isNotDigitThanThrow(String input) {
        if (!isDigit(input)) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void isRightOrderFormat(String order) {
        if (!order.matches("^([^,-]+)-(\\d+)(,\\s*[^,-]+-\\d+)*$")) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
