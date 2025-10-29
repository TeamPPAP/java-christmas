package christmas.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return details.stream()
            .map(OrderDetail::toString)
            .collect(Collectors.joining("\n"));
    }

    private List<OrderDetail> createOrderDetails(Map<String, Integer> order) {
        return order.entrySet().stream()
            .map(entry -> new OrderDetail(entry.getKey(), entry.getValue()))
            .collect(toList());
    }
}
