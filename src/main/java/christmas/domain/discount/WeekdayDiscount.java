package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.MenuType;
import christmas.domain.order.Orders;

public class WeekdayDiscount implements DiscountPolicy {

    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        return orders.countMenuByType(MenuType.DESSERT) * DISCOUNT_AMOUNT;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        return visitDate.isWeekday() && (0 <= orders.countMenuByType(MenuType.DESSERT));
    }

    @Override
    public String getDiscountName() {
        return "평일 할인";
    }

}
