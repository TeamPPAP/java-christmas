package christmas.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Order {
    private List<OrderDetail> details;
    private VisitDate orderDate;

    public Order(Map<String, Integer> order, LocalDate orderDate) {
        this.details = createOrderDetails(order);
        this.orderDate = new VisitDate(orderDate);
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public VisitDate getOrderDate() {
        return orderDate;
    }

    public int getTotalPrice() {
        return details.stream()
            .mapToInt(OrderDetail::getTotalPrice)
            .sum();
    }

    private List<OrderDetail> createOrderDetails(Map<String, Integer> order) {
        return order.entrySet().stream()
            .map(entry -> new OrderDetail(entry.getKey(), entry.getValue()))
            .collect(toList());
    }
}
