package christmas.service;

import christmas.domain.Order;
import christmas.service.benefit.EventBadgeAssigner;
import christmas.service.benefit.GiveawayPromotion;
import christmas.service.benefit.dto.Giveaway;
import christmas.service.benefit.value.Badge;

public class BenefitService {

    private EventBadgeAssigner eventBadgeAssigner;
    private GiveawayPromotion giveawayPromotion;

    public BenefitService() {
        this.eventBadgeAssigner = new EventBadgeAssigner();
        this.giveawayPromotion = new GiveawayPromotion();
    }

    public Giveaway GetGiveaway(Order order) {
        return giveawayPromotion.apply(order);
    }

    public Badge getEventBadge(int totalBenefitAmount) {
        return eventBadgeAssigner.apply(totalBenefitAmount);
    }
}
