package christmas.service.benefit;

import christmas.domain.Order;
import christmas.domain.value.Menu;
import christmas.service.benefit.dto.Giveaway;

import static christmas.domain.value.Menu.CHAMPAGNE;

public class GiveawayPromotion implements BenefitPolicy<Order, Giveaway> {
    private final Menu giveaway = CHAMPAGNE;
    private static final int THRESHOLD = 120_000;

    @Override
    public Giveaway apply(Order order) {
        if (order.getTotalPrice() < THRESHOLD || !order.isEligibleForEvent()) {
            return null;
        }
        return new Giveaway(giveaway, 1);
    }
}

