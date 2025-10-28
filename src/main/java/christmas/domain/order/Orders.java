package christmas.domain.order;

import christmas.domain.menu.MenuType;

import java.util.List;

/**
 * First-class collection representing multiple orders.
 * Validates total quantity limit (max 20) and ensures at least one non-beverage item.
 */
public class Orders {

    /** Immutable Collection of orders */
    private final List<Order> orders;

    /**
     * Constructs an Orders instance with validation.
     * @param orders list of orders to manage
     * @throws IllegalArgumentException if total quantity exceeds 20
     * @throws IllegalArgumentException if all orders are beverages only
     */
    public Orders(List<Order> orders) {
        this.orders = List.copyOf(orders);
        validateTotalQuantity(orders);
        validateNotOnlyBeverages(orders);
    }

    /**
     * Calculates the total price of all orders before discount.
     * @return sum of all order prices (menu price × quantity)
     */
    public int calculateTotalPrice() {
        return orders.stream()
                .mapToInt(Order::calculatePrice)
                .sum();
    }

    /**
     * Counts total quantity of menus matching the given type.
     * @param menuType the type to filter by (e.g., DESSERT, MAIN)
     * @return total quantity of matching menu type, or 0 if none found
     */
    public int countMenuByType(MenuType menuType) {
        return orders.stream().filter(order -> order.isMenuType(menuType))
                .mapToInt(Order::getQuantity)
                .sum();
    }

    /**
     * Returns the immutable list of orders.
     * @return unmodifiable list of orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Validates that total order quantity does not exceed 20.
     * @param orders list of orders to validate
     * @throws IllegalArgumentException if total quantity exceeds 20
     */
    private void validateTotalQuantity(List<Order> orders) {
        int totalQuantity = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();

        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 총 주문 개수는 20개 이하이어야 합니다.");
        }
    }

    /**
     * Validates that orders contain at least one non-beverage item.
     * @param orders list of orders to validate
     * @throws IllegalArgumentException if all orders are beverages
     */
    private void validateNotOnlyBeverages(List<Order> orders) {
        boolean allBeverages = orders.stream()
                .allMatch(order -> order.isMenuType(MenuType.BEVERAGE));

        if (allBeverages) {
            throw new IllegalArgumentException("[ERROR] 음식 메뉴를 최소 1개 이상 포함해야 합니다.");
        }
    }

    /**
     * Validates duplicate menu entries.
     * @param orders list of orders to validate
     * @deprecated Duplicate validation is handled at the parsing layer in Controller
     */
    @Deprecated
    private void validateNoDuplicateMenus(List<Order> orders) {
        return;
    }

}
