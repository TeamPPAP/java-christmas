package christmas.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Order {
    private List<OrderDetail> details;
    private LocalDate orderDate;

    public Order(Map<String, Integer> order, LocalDate orderDate) {
        this.orderDate = orderDate;
        this.details = order.entrySet().stream()
            .map(entry -> new OrderDetail(entry.getKey(), entry.getValue()))
            .collect(toList());
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
