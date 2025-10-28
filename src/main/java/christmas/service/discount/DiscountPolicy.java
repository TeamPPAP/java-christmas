package christmas.service.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface DiscountPolicy<T> {

    int calculateDiscount(T type);
    default boolean isWeekend (LocalDate localDate) {
        DayOfWeek value = localDate.getDayOfWeek();
        return  value == DayOfWeek.SATURDAY || value == DayOfWeek.FRIDAY;
    }
}
