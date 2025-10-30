package christmas.view;

import christmas.domain.entity.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.event.EventDetail;

import java.util.Arrays;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 포텐업 식당 12월 이벤트 플래너입니다.");
    }

    public void askVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void askForOrderDetails() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printEventPreviewMessage(int date) {
        System.out.println("12월 " + date +"일에 포텐업 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrderSummary(String orderSummary) {
        System.out.println("<주문메뉴>\n" + orderSummary);
    }

    public void printTotalBeforeDiscount(int totalBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>\n"
                + String.format("%,d", totalBeforeDiscount) + "원\n");
    }

    public void printEventDetails(EventDetail eventDetail) {
        System.out.println(eventDetail.toString());
    }

    public void printEvent(VisitDate visitDate, Order order) {
        System.out.println("<혜택 내역>");
        Arrays.stream(Event.values())
                .map(event -> {
                    event.getEventDetail().apply(visitDate, order);
                    return event.getEventDetail();
                })
                .forEach(event -> printEventDetails(event));
        System.out.println();
    }
}
