package christmas.service.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;

@FunctionalInterface
public interface DiscountPolicy<T> {

    int calculateDiscount(T type);

    default boolean isWeekend(LocalDate localDate) {
        DayOfWeek value = localDate.getDayOfWeek();
        return value == DayOfWeek.SATURDAY || value == DayOfWeek.FRIDAY;
    }

    LocalDate christmasDate = LocalDate.of(LocalDate.now().getYear(), 12, 25);
}
