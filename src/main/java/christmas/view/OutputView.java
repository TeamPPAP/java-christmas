package christmas.view;

import christmas.domain.benefit.Badge;
import christmas.domain.benefit.Giveaway;
import christmas.domain.date.VisitDate;
import christmas.domain.discount.Discounts;
import christmas.domain.order.Orders;

public class OutputView {

    public void printWelcome() {
        System.out.println("안녕하세요! 포텐업 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreviewHeader(VisitDate visitDate) {
        System.out.println();
        System.out.println("12월 " + visitDate.date() + "일에 포텐업 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrderMenu(Orders orders) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        orders.getOrders().forEach(
                order -> System.out.printf("%s %d개%n", order.getMenuName(), order.getQuantity()));
    }

    public void printTotalAmountBeforeDiscount(Orders orders) {
        System.out.println();
        System.out.println("<할인 전 총 주문 금액>");
        System.out.printf("%,d원%n", orders.calculateTotalAmount());
    }

    public void printGiveaway(Giveaway giveaway) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        System.out.println(giveaway.getMenu());
    }

    public void printBenefits(Discounts discounts) {
        System.out.println();
        System.out.println("<혜택 내역>");
        discounts.discounts().forEach(
                (key, value) -> System.out.printf("%s -%,d원%n", key, value));
    }

    public void printTotalBenefitAmount(Discounts discounts) {
        System.out.println();
        System.out.println("<총 혜택 금액>");
        System.out.printf("-%,d원%n", discounts.getTotalAmount());
    }

    public void printFinalAmount(Orders orders, Discounts discounts) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n", orders.calculateTotalAmount() - discounts.getTotalAmount());
    }

    public void printBadge(Badge badge) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getType());
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}
