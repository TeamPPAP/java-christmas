package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

public class WeekdayDiscount implements DiscountPolicy {
    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        // dessert count * 2025
        return 0;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        // isWeekday && has dessert
        return false;
    }

    @Override
    public String getDiscountName() {
        return "평일 할인";
    }
}
