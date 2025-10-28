package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

public class SpecialDiscount implements DiscountPolicy {
    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        // always 1000
        return 0;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        // isSpecialDay (Sunday or 25th)
        return false;
    }

    @Override
    public String getDiscountName() {
        return "특별 할인";
    }
}
