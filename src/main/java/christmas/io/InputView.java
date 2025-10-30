package christmas.io;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.OrderDetail;
import christmas.domain.VisitDate;
import christmas.exception.ExceptionHandler;
import christmas.util.ObjectUtil;

import java.util.List;

import static christmas.util.ObjectUtil.isBlank;
import static christmas.util.ObjectUtil.isDigit;
import static christmas.util.ObjectUtil.split;

public class InputView {

    public VisitDate readDate() {
        System.out.println("안녕하세요! 포텐업 식당 12월 이벤트 플래너입니다.");
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                isValidAnswer(input);
                isNotDigitThanThrow(input);

                return new VisitDate(input);
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
    }

    public List<OrderDetail> readMenu() {
        while (true) {
            try {
                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String input = Console.readLine();

                isValidAnswer(input);
                isRightOrderFormat(input);
                List<String> split = List.of(split(input, ","));
                return OrderDetail.createOrderDetail(split);
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
