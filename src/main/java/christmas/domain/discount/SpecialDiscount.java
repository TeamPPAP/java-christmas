package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;
import java.time.LocalDate;
import java.util.Set;

public class SpecialDiscount implements DiscountPolicy {

    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;

    private static final Set<LocalDate> SPECIAL_DAYS = Set.of(
            LocalDate.of(2025, 12, 7),
            LocalDate.of(2025, 12, 14),
            LocalDate.of(2025, 12, 21),
            LocalDate.of(2025, 12, 25),
            LocalDate.of(2025, 12, 28)
    );

    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        return SPECIAL_DISCOUNT_AMOUNT;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        return SPECIAL_DAYS.contains(visitDate.getDate());
    }

    @Override
    public String getDiscountName() {
        return "특별 할인";
    }

}
