package christmas.domain.model;

import christmas.domain.model.constant.Category;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static christmas.domain.model.message.ErrorMessage.*;

public final class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        if(orders.isEmpty()){
            throw new IllegalArgumentException(NO_ORDER_ADDED.getMessage());
        }
        ensureDuplicateOrder(orders);
        this.orders = List.copyOf(orders);
    }

    private void ensureDuplicateOrder(List<Order> orders){
        Set<String> orderString = orders.stream().map(order -> order.orderMenu().menuName()).collect(Collectors.toSet());
        if(orderString.size()!=orders.size()){
            throw new IllegalArgumentException(DUPLICATE_MENU.getMessage());
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
    public int quantityOfOrderByCategory(Category category){
        return orders.stream()
                .filter(o -> o.getOrderMenu().getCategory() == category)
                .mapToInt(Order::getQuantity)
                .sum();
    }
}
