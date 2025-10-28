package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

import java.time.LocalDate;
import java.util.List;

public class SpecialDiscount implements DiscountPolicy {

    private List<LocalDate> specialDiscountDays = List.of(
            LocalDate.of(2025,12,7),
            LocalDate.of(2025,12,14),
            LocalDate.of(2025, 12, 21),
            LocalDate.of(2025, 12, 25),
            LocalDate.of(2025, 12, 28)
    );

    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        // TODO
        // always 1000
        return 0;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        return specialDiscountDays.contains(visitDate.date());
    }

    @Override
    public String getDiscountName() {
        return "특별 할인";
    }
}
