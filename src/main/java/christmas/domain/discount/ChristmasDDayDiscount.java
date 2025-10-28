package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

public class ChristmasDDayDiscount implements DiscountPolicy {

    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        return 1_000 + (visitDate.date().getDayOfMonth() - 1) * 100;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        // LocalDate 타입에서 기본 값이 무조건 1 이상 이기에 검증하지 않음.
        int day = visitDate.date().getDayOfMonth();
        return day <= 25;
    }

    @Override
    public String getDiscountName() {
        return "크리스마스 디데이 할인";
    }

}
