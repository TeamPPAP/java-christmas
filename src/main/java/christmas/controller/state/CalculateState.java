package christmas.controller.state;

import christmas.domain.model.Badge;
import christmas.domain.model.Order;
import christmas.service.dto.BenefitResult;

public class CalculateState implements State{
    @Override
    public void stateHandler(StateContext context) {
        try{
            context.validateOrders();
        }catch (IllegalArgumentException e){
            System.out.printf(e.getMessage());
        }
        BenefitResult result = calBenefit(context);
        printResults(context, result);
        terminateStateMachine(context);
    }

    private BenefitResult calBenefit(StateContext context){
        return context.calculateBenefit();
    }

    private void printResults(StateContext context, BenefitResult result) {
        printOrderSummary(context);
        printBenefitDetails(result);
        printBadge(result);
    }

    private void printOrderSummary(StateContext context) {
        System.out.println("\n==========<주문 메뉴>==========");
        for (Order order : context.getOrders().getOrdersToList()) {
            System.out.println(order.getOrderMenu().getMenuName() + " - " + order.getQuantity() + "개");
        }

        int totalBefore = calTotalBefore(context);
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(formatAmount(totalBefore) + "원");
    }

    private int calTotalBefore(StateContext context) {
        return context.getOrders().totalOrderAmount();
    }

    private void printBenefitDetails(BenefitResult result) {
        printGiftMenu(result);
        printBenefitList(result);
        printTotalBenefit(result);
        printFinalAmount(result);
    }

    private void printGiftMenu(BenefitResult result) {
        System.out.println("\n<증정 메뉴>");
        result.gift().ifPresentOrElse(
                gift -> System.out.println(gift.giftMenu().getMenuName() + " " + gift.quantity() + "개"),
                () -> System.out.println("없음")
        );
    }

    private void printBenefitList(BenefitResult result) {
        System.out.println("\n<혜택 내역>");
        boolean hasBenefits = !result.benefitList().isEmpty() || result.gift().isPresent();

        if (!hasBenefits) {
            System.out.println("없음");
            return;
        }

        result.benefitList().forEach(event ->
                System.out.println(event.eventType() + ": -" + formatAmount(event.discountAmount()) + "원")
        );
        result.gift().ifPresent(gift ->
                System.out.println("증정 이벤트: -" + formatAmount(gift.giftMenu().getPrice()) + "원")
        );
    }

    private void printTotalBenefit(BenefitResult result) {
        System.out.println("\n<총혜택 금액>");
        System.out.println("-" + formatAmount(result.totalBenefitAmount()) + "원");
    }

    private void printFinalAmount(BenefitResult result) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(formatAmount(result.finalBillsAmount()) + "원");
    }

    private void printBadge(BenefitResult result) {
        Badge badge = Badge.awardBadge(result.totalBenefitAmount());
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge.getName());
        System.out.println("\n===========================");
    }

    private String formatAmount(int amount) {
        return String.format("%,d", amount);
    }

    private void terminateStateMachine(StateContext context) {
        while (!context.isEmpty()) {
            context.pop();
        }
    }
}
