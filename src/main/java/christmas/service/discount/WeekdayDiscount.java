package christmas.service.discount;

import christmas.domain.Order;
import christmas.service.DiscountContext;
import christmas.service.discount.dto.DiscountResult;

public class WeekdayDiscount implements DiscountPolicy, DiscountCondition<Order> {

    @Override
    public DiscountResult calculateDiscount(DiscountContext context) {
        Order order = context.order();
        if (isSatisfiedBy(order)) {
            return DiscountResult.to("평일 할인", getDessertCnt(order));
        }
        return null;
    }

    private int getDessertCnt(Order order) {
        return (int) order.countDessertItems() * DISCOUNT_PER_MENU;
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return !order.getVisitDate().isWeekend() && order.isEligibleForEvent();
    }
}
