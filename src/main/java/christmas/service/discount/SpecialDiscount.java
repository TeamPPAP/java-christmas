package christmas.service.discount;

import christmas.domain.Order;
import christmas.domain.VisitDate;

public class SpecialDiscount implements DiscountPolicy<Order>, DiscountCondition<VisitDate> {
    private final int discountAmount = 1000;

    @Override
    public int calculateDiscount(Order order) {
        if (isSatisfiedBy(order.getOrderDate())) {
            return discountAmount;
        }
        return 0;
    }

    @Override
    public boolean isSatisfiedBy(VisitDate date) {
        return date.isSunday() || date.isChristmas();
    }
}
