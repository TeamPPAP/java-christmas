package christmas.view;

import christmas.domain.model.Order;

import java.util.List;

public class OutputView {

    public OutputView() {
    }

    public void printGreeting() {
        System.out.println("안녕하세요! 포텐업 식당 12월 이벤트 플래너입니다.");
    }

    public void printVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printSeparator() {
        System.out.println();
    }

    public void printCurrentOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("📋 현재 주문 내역: 없음");
            return;
        }
        System.out.println("📋 현재 주문 내역:");
        for (Order o : orders) {
            System.out.println("  - " + o.getOrderMenu().getMenuName() + " " + o.getQuantity() + "개");
        }
    }

    public void printCategorySelect() {
        System.out.println("\n==== 메뉴 카테고리 선택 ====");
        System.out.println("주문하실 메뉴의 카테고리를 선택해주세요.");
    }

    public void printMenuSelect(String categoryName) {
        System.out.println("\n==== " + categoryName + " 메뉴 선택 ====");
        System.out.println("주문하실 메뉴를 선택해주세요.");
    }

    public void printQuantity(String menuName) {
        System.out.println("\n==== 수량 입력 ====");
        System.out.println("'" + menuName + "'의 주문 수량을 입력해주세요. (1 이상의 숫자)");
    }
}
