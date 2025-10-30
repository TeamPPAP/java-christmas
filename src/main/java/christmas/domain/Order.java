package christmas.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Order {
    private List<OrderDetail> details;
    private VisitDate visitDate;
    private boolean isAllDrinks = false;
    private int totalPrice = 0;

    public Order(Map<String, Integer> order, LocalDate visitDate) {
        List<OrderDetail> orderDetails = createOrderDetails(order);
        if (orderDetails.isEmpty() || orderDetails.size() > 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        this.details = orderDetails;
        this.visitDate = new VisitDate(visitDate);
        this.totalPrice = createTotalPrice(orderDetails);
        this.isAllDrinks = checkAllDrinks(orderDetails);
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public long countDessertItems() {
        return details.stream()
            .filter(orderDetail -> orderDetail.getMenu().isDessert())
            .count();
    }

    public long countMainItems() {
        return details.stream()
            .filter(orderDetail -> orderDetail.getMenu().isMainMenu())
            .count();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isEligibleForEvent() {
        return getTotalPrice() >= 10_000 && !isAllDrinks;
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

    private int createTotalPrice(List<OrderDetail> details) {
        return details.stream()
            .mapToInt(OrderDetail::getTotalPrice)
            .sum();
    }

    private boolean checkAllDrinks(List<OrderDetail> details) {
        return details.stream()
            .allMatch(detail -> detail.getMenu().isDessert());
    }
}
