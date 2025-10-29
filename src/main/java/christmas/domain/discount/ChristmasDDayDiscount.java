package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

public class ChristmasDDayDiscount implements DiscountPolicy {

    private static final int BASE_DISCOUNT_AMOUNT = 1_000;
    private static final int ADDITIONAL_DISCOUNT_AMOUNT_PER_DAY = 100;
    private static final int CHRISTMAS = 25;

    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        return BASE_DISCOUNT_AMOUNT + (visitDate.date().getDayOfMonth() - 1) * ADDITIONAL_DISCOUNT_AMOUNT_PER_DAY;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        int day = visitDate.date().getDayOfMonth();
        return day <= CHRISTMAS;
    }

    @Override
    public String getDiscountName() {
        return "크리스마스 디데이 할인";
    }

}
