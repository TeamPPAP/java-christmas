package christmas.service.discount;

import christmas.domain.Order;
import christmas.domain.OrderDetail;

public class WeekdayDiscount implements DiscountPolicy<Order>, DiscountCondition<Order> {

    @Override
    public int calculateDiscount(Order order) {
        int discount = 0;
        if (isSatisfiedBy(order)) {
            discount = getDessertCnt(order) * DISCOUNT_PER_MENU;
        }
        return discount;
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
