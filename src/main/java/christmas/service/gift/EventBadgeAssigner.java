package christmas.service.gift;

import christmas.service.gift.value.Badge;

public class EventBadgeAssigner implements BenefitPolicy<Integer, Badge> {

    @Override
    public Badge apply(Integer totalBenefitAmount) {
        return Badge.getBadge(totalBenefitAmount);
    }
}
