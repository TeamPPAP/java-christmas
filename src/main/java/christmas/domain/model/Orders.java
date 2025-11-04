package christmas.domain.model;

import christmas.domain.model.constant.Category;

import java.util.List;

import static christmas.domain.model.message.ErrorMessage.*;

public final class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        ensureOrdersEmpty();
        ensureNotOnlyDrink();
        totalQuantityOfOrder();
        this.orders = List.copyOf(orders);
    }

    private void ensureOrdersEmpty(){
        if(orders.isEmpty()){
            throw new IllegalArgumentException(NO_ORDER_ADDED.getMessage());
        }
    }

    private void ensureNotOnlyDrink(){
        if(orders.stream().allMatch(order -> Category.DRINK == order.getOrderMenu().getCategory())){
            throw new IllegalArgumentException(DRINK_ONLY.getMessage());
        }
    }

    public boolean isSatisfyEventPolicy(){
        int totalAmount = totalOrderAmount();
        return totalAmount >= 10000;
    }

    public int totalOrderAmount() {
        return orders.stream().mapToInt(Order::getOrderAmount).sum();
    }

    public int totalQuantityOfOrder(){
        int totalOrderQuantity = orders.stream().mapToInt(Order::quantity).sum();
        if(totalOrderQuantity>20){
            throw new IllegalArgumentException(ORDER_LIMIT_EXCEEDED.getMessage());
        }
        return totalOrderQuantity;
    }

    public List<Order> getOrdersToList() {
        return orders;
    }

    public int quantityOfOrderByCategory(Category category){
        return orders.stream()
                .filter(o -> o.getOrderMenu().getCategory() == category)
                .mapToInt(Order::getQuantity)
                .sum();
    }
}
