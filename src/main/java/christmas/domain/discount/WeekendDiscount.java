package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

public class WeekendDiscount implements DiscountPolicy {
    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        // TODO main count * 2025
        return 0;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        // TODO isWeekend && has main
        return false;
    }

    @Override
    public String getDiscountName() {
        return "주말 할인";
    }
}
