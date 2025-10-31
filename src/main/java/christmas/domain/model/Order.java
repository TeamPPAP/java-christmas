package christmas.domain.model;

import static christmas.domain.model.message.ErrorMessage.NO_ORDER_ADDED;
import static christmas.domain.model.message.ErrorMessage.ORDER_QTY_ZERO;

public record Order(Menu orderMenu, int quantity) {

    public Order {
        if(orderMenu == null){
            throw new IllegalArgumentException(NO_ORDER_ADDED.getMessage());
        }
        if(quantity < 1){
            throw new IllegalArgumentException(ORDER_QTY_ZERO.getMessage());
        }
    }

    public Menu getOrderMenu() {
        return orderMenu;
    }
    public int getQuantity() {
        return quantity;
    }

    public int getOrderAmount(){return this.quantity * this.orderMenu.getPrice();}
    public int getOnlyOrderPrice() {
        return this.orderMenu.getPrice();
    }
}