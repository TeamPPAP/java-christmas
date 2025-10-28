package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.MenuType;
import christmas.domain.order.Orders;

public class WeekendDiscount implements DiscountPolicy {

    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        return orders.countMenuByType(MenuType.MAIN) * DISCOUNT_AMOUNT;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        return visitDate.isWeekend() && orders.countMenuByType(MenuType.MAIN) > 0;
    }

    @Override
    public String getDiscountName() {
        return "주말 할인";
    }

}
