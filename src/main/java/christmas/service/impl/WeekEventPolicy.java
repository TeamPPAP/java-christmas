package christmas.service.impl;

import christmas.domain.model.Orders;
import christmas.domain.model.constant.Category;
import christmas.service.dto.Event;
import christmas.service.EventPolicy;
import christmas.util.validator.EventValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static christmas.domain.model.defualtAmount.DefaultAmount.WEEK_DISCOUNT_AMOUNT;

public class WeekEventPolicy implements EventPolicy {

    public WeekEventPolicy() {
    }

    @Override
    public Optional<Event> applyEvent(Orders orders, int date) {
        if (!orders.isSatisfyEventPolicy()) {
            return Optional.empty();
        }
        Event event = createEvent(orders, date);
        return Optional.of(event);
    }

    private Event createEvent(Orders orders, int date) {
        Category category = decideDiscountCategory(date);
        int discountAmount = orders.quantityOfOrderByCategory(category) * WEEK_DISCOUNT_AMOUNT.getAmount();

        String eventName = isWeekend(date) ? "주말 할인" : "평일 할인";
        return new Event(eventName, discountAmount);
    }

    private Category decideDiscountCategory(int date) {
        if (isWeekend(date)) {
            return Category.MAIN_DISH;
        }
        return Category.DESSERT;
    }

    private boolean isWeekend(int date) {
        LocalDate localDate = LocalDate.of(2025, Month.DECEMBER, date);
        DayOfWeek day = localDate.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }
}
