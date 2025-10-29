package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountCalculator {

    private static final int MIN_AMOUNT_FOR_DISCOUNT = 10_000;

    private final Orders orders;
    private final VisitDate visitDate;
    private final List<DiscountPolicy> discountPolicies = List.of(
            new ChristmasDDayDiscount(),
            new WeekdayDiscount(),
            new WeekendDiscount(),
            new SpecialDiscount()
    );

    public DiscountCalculator(Orders orders, VisitDate visitDate) {
        this.orders = orders;
        this.visitDate = visitDate;
    }

    public Discounts calculateDiscounts() {
        if (MIN_AMOUNT_FOR_DISCOUNT <= orders.calculateTotalAmount()) {
            return new Discounts(Collections.emptyMap());
        }
        return new Discounts(
                discountPolicies.stream()
                        .filter(policy -> policy.isApplicable(visitDate, orders))
                        .collect(Collectors.toMap(
                                DiscountPolicy::getDiscountName,
                                policy -> policy.calculateDiscount(visitDate, orders)
                        ))
        );
    }

}
