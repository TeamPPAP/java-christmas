package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

public class Order {

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        validateQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    public int calculatePrice() {
        return this.menu.getPrice() * this.quantity;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isMenuType(MenuType menuType) {
        return this.menu.getType().equals(menuType);
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문 개수입니다. 다시 입력해 주세요.");
        }
    }

}
