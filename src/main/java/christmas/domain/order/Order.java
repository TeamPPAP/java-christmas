package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

public class Order {

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        // TODO: quantity 1 이상 검증
        this.menu = menu;
        this.quantity = quantity;
    }

    public int calculatePrice() {
        // TODO: menu 가격 * quantity 반환
        return 0;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isMenuType(MenuType menuType) {
        return this.menu.getType().equals(menuType);
    }

    private boolean isQuantityValid(int quantity) {
        return quantity >= 1;
    }

}
