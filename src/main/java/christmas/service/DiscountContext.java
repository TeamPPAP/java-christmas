package christmas.service;

import christmas.domain.Order;
import christmas.domain.VisitDate;

public record DiscountContext(Order order) {

    public VisitDate getVisitDate() {
        return order.getVisitDate();
    }

}
