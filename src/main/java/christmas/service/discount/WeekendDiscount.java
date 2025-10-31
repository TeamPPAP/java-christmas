package christmas.service.discount;

import christmas.domain.Order;
import christmas.service.DiscountContext;
import christmas.service.discount.dto.DiscountResult;

public class WeekendDiscount implements DiscountPolicy, DiscountCondition<Order> {

    @Override
    public DiscountResult calculateDiscount(DiscountContext context) {
        Order order = context.order();
        if (isSatisfiedBy(order)) {
            return DiscountResult.to("주말 할인", getMainMenuCnt(order));
        }
        return null;
    }

    private int getMainMenuCnt(Order order) {
        return (int) order.countMainItems() * DISCOUNT_PER_MENU;
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.getVisitDate().isWeekend() && order.isEligibleForEvent();
    }
}
