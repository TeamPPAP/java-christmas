package christmas.service.discount;

import christmas.domain.Order;
import christmas.domain.VisitDate;

public class ChristmasDiscount implements DiscountPolicy<Order> {

    @Override
    public int calculateDiscount(Order order) {
        VisitDate date = order.getOrderDate();
        if (date.isAfterChristmas()) {
            return 0;
        }

        return (date.getDay() - 1) * 100 + 1000;
    }
}
