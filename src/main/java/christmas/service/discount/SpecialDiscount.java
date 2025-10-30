package christmas.service.discount;

import christmas.domain.VisitDate;
import christmas.service.DiscountContext;
import christmas.service.discount.dto.DiscountResult;

public class SpecialDiscount implements DiscountPolicy, DiscountCondition<DiscountContext> {
    private static final int DISCOUNT_AMOUNT = 1000;

    @Override
    public DiscountResult calculateDiscount(DiscountContext context) {
        if (isSatisfiedBy(context)) {
            return DiscountResult.to("특별 할인", DISCOUNT_AMOUNT);
        }
        return null;
    }

    @Override
    public boolean isSatisfiedBy(DiscountContext context) {
        VisitDate date = context.getVisitDate();
        return (date.isSunday() || date.isChristmas()) && context.order().isEligibleForEvent();
    }
}
