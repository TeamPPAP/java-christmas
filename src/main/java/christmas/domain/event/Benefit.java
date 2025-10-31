package christmas.domain.event;

import christmas.domain.order.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.menu.MenuCategory;

import java.time.LocalDate;

public interface Benefit {
    LocalDate EVENT_START_DATE = LocalDate.of(2025, 12, 1);
    LocalDate EVENT_END_DATE = LocalDate.of(2025, 12, 31);
    int MINIMUM_ORDER_PRICE = 10000;

    public void apply(VisitDate visitDate, Order order);

    default boolean isApplicable(VisitDate visitDate, Order order) {
        if (!visitDate.isDuringPeriod(EVENT_START_DATE, EVENT_END_DATE)) {
            return false;
        }

        if (order.getTotalPrice() < MINIMUM_ORDER_PRICE) {
            return false;
        }

        if (order.containsOnlyCategory(MenuCategory.DRINK)) {
            return false;
        }

        return true;
    }
}