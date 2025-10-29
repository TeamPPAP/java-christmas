package christmas.domain.model;

public class Order {
    Menu orderMenu;
    int quantity;

    public Order(Menu order, int quantity) {
        this.orderMenu = order;
        this.quantity = quantity;
    }

    public Menu getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(Menu orderMenu) {
        this.orderMenu = orderMenu;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderPrice() {
        return this.orderMenu.getPrice();
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
