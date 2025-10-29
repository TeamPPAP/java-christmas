package christmas.service.discount;

import christmas.domain.Order;
import christmas.domain.OrderDetail;
import christmas.service.discount.dto.DiscountResult;

public class WeekdayDiscount implements DiscountPolicy<Order>, DiscountCondition<Order> {

    @Override
    public DiscountResult calculateDiscount(Order order) {
        if (isSatisfiedBy(order)) {
            return new DiscountResult("평일 할인", getDessertCnt(order) * DISCOUNT_PER_MENU);
        }
        return null;
    }

    private int getDessertCnt(Order order) {
        return order.getDetails().stream()
            .filter(orderDetail -> orderDetail.getMenu().isDessert())
            .mapToInt(OrderDetail::getCnt)
            .sum();
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return !order.getOrderDate().isWeekend();
    }
}
