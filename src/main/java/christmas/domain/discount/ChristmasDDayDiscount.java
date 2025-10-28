package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

public class ChristmasDDayDiscount implements DiscountPolicy {
    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        // 1000 + (day - 1) * 100
        return 0;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        // 1 <= day <= 25
        return false;
    }

    @Override
    public String getDiscountName() {
        return "크리스마스 디데이 할인";
    }
}
