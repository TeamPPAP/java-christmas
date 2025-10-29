package christmas.io;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.service.gift.value.Badge;

import java.util.Map;

import static christmas.util.ObjectUtil.formatPrice;

public class OutputView {

    public void printEventPreviewTitle(VisitDate visitDate) {
        System.out.printf("12월 %d일에 포텐업 식당에서 받을 이벤트 혜택 미리 보기!%n%n", visitDate.getDay());
    }

    public void printOrderItems(Order order) {
        System.out.println("<주문 메뉴>");
        System.out.println(order);
        System.out.println();
    }

    public void printTotalAmountBeforeDiscount(int totalAmount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatPrice(totalAmount));
        System.out.println();
    }

    public void printGiveaway(String giveawayItem) {
        System.out.println("<증정 메뉴>");
        System.out.println(giveawayItem);
        System.out.println();
    }

    public void printBenefitDetails(Map<String, Integer> benefitDetails) {
        System.out.println("<혜택 내역>");
        if (benefitDetails == null || benefitDetails.isEmpty()) {
            System.out.println("없음\n");
            return;
        }
        benefitDetails.forEach((name, amount) ->
            System.out.printf("%s: -%s%n", name, formatPrice(amount))
        );
        System.out.println();
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println("<총혜택 금액>");
        if (totalBenefitAmount == 0) {
            System.out.println("0원\n");
            return;
        }
        System.out.printf("-%s%n", formatPrice(totalBenefitAmount));
        System.out.println();
    }

    public void printFinalPayment(int finalPayment) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(formatPrice(finalPayment));
        System.out.println();
    }

    public void printEventBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getLabel());
        System.out.println();
    }
}
