package christmas.service.discount;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.service.discount.dto.DiscountResult;

public class ChristmasDiscount implements DiscountPolicy<Order> {

    @Override
    public DiscountResult calculateDiscount(Order order) {
        VisitDate date = order.getOrderDate();
        if (date.isAfterChristmas()) {
            return null;
        }

        return new DiscountResult("크리스마스 디데이 할인", (date.getDay() - 1) * 100 + 1000);
    }
}
