package christmas.service.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscount implements DiscountPolicy<LocalDate> {
    private final int discountAmount = 1000;

    @Override
    public int calculateDiscount(LocalDate o) {
        if (isSpecialDay(o)) {
            return discountAmount;
        }
        return 0;
    }

    private boolean isSpecialDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || date.equals(christmasDate);
    }
}
