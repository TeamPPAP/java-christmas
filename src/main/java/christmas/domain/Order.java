package christmas.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private List<OrderDetail> details;
    private VisitDate visitDate;
    private boolean isAllDrinks = false;
    private int totalPrice = 0;

    public Order(List<OrderDetail> details, VisitDate visitDate) {
        this.details = details;
        this.visitDate = visitDate;
        this.totalPrice = createTotalPrice(details);
        this.isAllDrinks = checkAllDrinks(details);
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
