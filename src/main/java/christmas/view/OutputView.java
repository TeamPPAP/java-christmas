package christmas.view;

import christmas.domain.model.Order;

import java.util.List;

public class OutputView {

    public OutputView() {
    }

    public void printGreeting() {
        System.out.println("안녕하세요! 포텐업 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printTakeOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printBenefitPreview() {
        System.out.println("12월 3일에 포텐업 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printSelectedMenu(List<Order> orders) {
        System.out.println("<주문 메뉴>");
        for (Order order : orders) {
            System.out.printf("%s %d개\n", order.getOrderMenu().getMenuName(), order.getQuantity());
        }
    }

    public void printBeforeBenefitAffect(int amount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%d원\n", amount);
    }

    public void printGift(boolean isGift) {
        String gift = "없음";
        System.out.println("<증정 메뉴>");
        if (isGift) {
            gift = "샴페인 1개";
        }
        System.out.printf("%s\n", gift);
    }


    public void printTotalPrice(int totalPrice) {
        System.out.println("<총혜택 금액>");
        System.out.printf("-%d원\n", totalPrice);
    }

    public void printExpectPurchaseAmount(int finalPurchaseAmount){
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%d원\n",finalPurchaseAmount);
    }

    public void printBadgeAward(String badgeName){
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeName);
    }

}
