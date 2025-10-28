package christmas.domain.order;

import christmas.domain.menu.MenuType;
import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = List.copyOf(orders);
        validateTotalQuantity(orders);
        validateNotOnlyBeverages(orders);
    }

    public int calculateTotalAmount() {
        return orders.stream()
                .mapToInt(Order::calculatePrice)
                .sum();
    }

    public int countMenuByType(MenuType menuType) {
        return orders.stream().filter(order -> order.isMenuType(menuType))
                .mapToInt(Order::getQuantity)
                .sum();
    }

    public List<Order> getOrders() {
        return orders;
    }

//    private void validateNoDuplicateMenus(List<Order> orders) {
//        // TODO: 중복 메뉴 검증
//        // 만약, 사용자가 시저 샐러드 - 1, 시저 샐러드 - 1로 입력하면 총 시저 샐러드는 2가 되어야 하기 때문에 필요한 검증 절차.
//    }

    private void validateTotalQuantity(List<Order> orders) {
        int totalQuantity = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();

        if (20 < totalQuantity) {
            throw new IllegalArgumentException("[ERROR] 총 주문 개수는 20개 이하이어야 합니다.");
        }
    }

    private void validateNotOnlyBeverages(List<Order> orders) {
        boolean allBeverages = orders.stream()
                .allMatch(order -> order.isMenuType(MenuType.BEVERAGE));

        if (allBeverages) {
            throw new IllegalArgumentException("[ERROR] 음식 메뉴를 최소 1개 이상 포함해야 합니다.");
        }
    }

}
