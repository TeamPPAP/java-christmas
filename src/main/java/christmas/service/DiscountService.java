package christmas.service;

import christmas.domain.Order;
import christmas.service.discount.factory.DiscountFactory;

public class DiscountService {
    private Order order;

    public int calculateTotalDiscount() {
        return DiscountFactory.getInstance().getAllStrategies().stream()
            .mapToInt(discountPolicy -> discountPolicy.calculateDiscount(order))
            .sum();
    }

}
