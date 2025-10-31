package christmas.service.impl;

import christmas.domain.model.Orders;
import christmas.domain.model.constant.Category;
import christmas.service.EventPolicy;
import christmas.util.validator.EventValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static christmas.domain.model.defualtAmount.DefaultAmount.WEEK_DISCOUNT_AMOUNT;

public class WeekEventPolicy implements EventPolicy {
    private final EventValidator eventValidator;

    public WeekEventPolicy(EventValidator eventValidator) {
        this.eventValidator = eventValidator;
    }

    @Override
    public boolean isSatisfy(Orders orders, int date) {
        LocalDate localDate = LocalDate.of(2025, Month.DECEMBER, date);
        DayOfWeek day = localDate.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    @Override
    public int discountAmount(Orders orders, int date) {
        Category category = decideDiscountCategory(orders, date);
        return orders.quantityOfOrderByCategory(category) * WEEK_DISCOUNT_AMOUNT.getAmount();
    }

    @Override
    public String eventVariety() {
        return "";
    }

    private Category decideDiscountCategory(Orders orders,int date) {
        if (isSatisfy(orders,date)) {
            return Category.MAIN_DISH;
        }
        return Category.DESSERT;
    }
}
