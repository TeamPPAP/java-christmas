package christmas.service.discount;

import christmas.domain.VisitDate;
import christmas.service.DiscountContext;
import christmas.service.discount.dto.DiscountResult;

public class ChristmasDiscount implements DiscountPolicy, DiscountCondition<DiscountContext> {
    private static final int DISCOUNT_START_AMOUNT = 1000;
    private static final int DISCOUNT_RATE_PER_DAY = 100;

    @Override
    public DiscountResult calculateDiscount(DiscountContext context) {
        if (isSatisfiedBy(context)) {
            return null;
        }
        return DiscountResult.to("크리스마스 디데이 할인", calculateDiscountAmount(context.getVisitDate()));
    }

    private int calculateDiscountAmount(VisitDate visitDate) {
        int daysElapsed = visitDate.getDay() - 1;
        return DISCOUNT_START_AMOUNT + (daysElapsed * DISCOUNT_RATE_PER_DAY);
    }

    @Override
    public boolean isSatisfiedBy(DiscountContext context) {
        return !context.getVisitDate().isAfterChristmas() && context.order().isEligibleForEvent();
    }
}
