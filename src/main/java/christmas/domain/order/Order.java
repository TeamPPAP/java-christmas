package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

public class Order {

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        if (!isQuantityValid(quantity))
            throw new IllegalArgumentException("[ERROR] Quantity must be greater than or equal to 1.");
        this.menu = menu;
        this.quantity = quantity;
    }

    public int calculatePrice() {
        return this.menu.getPrice() * this.quantity;
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
