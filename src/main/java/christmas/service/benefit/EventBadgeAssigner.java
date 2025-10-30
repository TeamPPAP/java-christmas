package christmas.service.benefit;

import christmas.service.benefit.value.Badge;

public class EventBadgeAssigner implements BenefitPolicy<Integer, Badge> {

    @Override
    public Badge apply(Integer totalBenefitAmount) {
        return Badge.getBadge(totalBenefitAmount);
    }
}
