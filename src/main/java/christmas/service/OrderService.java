package christmas.service;

import christmas.domain.model.Order;
import java.util.List;

public class OrderService {

    public OrderService() {}

    public int totalOrderPrice(List<Order> orders){
        return orders.stream().mapToInt(order ->
                order.getOrderPrice() * order.getQuantity()).sum();
    }
}
