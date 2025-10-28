package christmas.view;

import christmas.domain.discount.Discounts;
import christmas.domain.order.Orders;

public class OutputView {

    public void printWelcome() {
        System.out.println("안녕하세요! 포텐업 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreviewHeader(int date) {
        System.out.println();
        System.out.println("12월 " + date + "일에 포텐업 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrderMenu(Orders orders) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        // 주문 메뉴 출력
    }

    public void printTotalAmountBeforeDiscount(int amount) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", amount));
    }

    public void printGiveaway(String giveaway) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        System.out.println(giveaway);
    }

    public void printBenefits(Discounts discounts, int giveawayAmount) {
        System.out.println();
        System.out.println("<혜택 내역>");
        // 할인 내역 출력
    }

    public void printTotalBenefitAmount(int amount) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println(String.format("-%,d원", amount));
    }

    public void printFinalAmount(int amount) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", amount));
    }

    public void printBadge(String badge) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
    
}
