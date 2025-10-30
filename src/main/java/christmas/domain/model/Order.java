package christmas.domain.model;

public class Order {
    private final Menu orderMenu;
    private final int quantity;

    public Order(Menu order, int quantity) {
        this.orderMenu = order;
        this.quantity = quantity;
    }

    public Menu getOrderMenu() {
        return orderMenu;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderPrice() {
        return this.orderMenu.getPrice();
    }

}
