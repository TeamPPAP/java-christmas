package christmas.service.discount;

import christmas.domain.Order;
import christmas.domain.OrderDetail;
import christmas.domain.value.MenuType;

public class WeekendDiscount implements DiscountPolicy<Order> {

    @Override
    public int calculateDiscount(Order o) {
        int discount = 0;
        if (isWeekend(o.getOrderDate())) {
            discount = getDessertCnt(o) * 2025;
        }
        return discount;

    }

    private int getDessertCnt(Order order) {
        return order.getDetails().stream()
                .filter(orderDetail -> orderDetail.getMenu().getMenuType() == MenuType.MAIN_MENU)
                .mapToInt(OrderDetail::getCnt)
                .sum();
    }

}
