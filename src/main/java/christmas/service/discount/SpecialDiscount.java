package christmas.service.discount;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.service.discount.dto.DiscountResult;

public class SpecialDiscount implements DiscountPolicy<Order>, DiscountCondition<VisitDate> {
    private final int discountAmount = 1000;

    @Override
    public DiscountResult calculateDiscount(Order order) {
        if (isSatisfiedBy(order.getOrderDate())) {
            return new DiscountResult("특별 할인", discountAmount);
        }
        return null;
    }

    @Override
    public boolean isSatisfiedBy(VisitDate date) {
        return date.isSunday() || date.isChristmas();
    }
}
