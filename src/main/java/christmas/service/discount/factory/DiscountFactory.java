package christmas.service.discount.factory;

import christmas.service.discount.ChristmasDiscount;
import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.SpecialDiscount;
import christmas.service.discount.WeekdayDiscount;
import christmas.service.discount.WeekendDiscount;

import java.util.List;

public class DiscountFactory {
    private static final DiscountFactory INSTANCE = new DiscountFactory();

    public static DiscountFactory getInstance() {
        return INSTANCE;
    }

    private final List<DiscountPolicy> policies;

    private DiscountFactory() {
        this.policies = List.of(
            new ChristmasDiscount(),
            new SpecialDiscount(),
            new WeekdayDiscount(),
            new WeekendDiscount()
        );
    }

    public List<DiscountPolicy> getAllStrategies() {
        return policies;
    }
}
