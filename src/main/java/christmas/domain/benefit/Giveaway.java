package christmas.domain.benefit;

import christmas.domain.menu.Menu;
import christmas.domain.order.Orders;

public class Giveaway {

    private static final int PROMOTION_EVENT_AMOUNT = 120_000;
    private final boolean isEligible;

    private Giveaway(boolean isEligible) {
        this.isEligible = isEligible;
    }

    public static Giveaway from(Orders orders) {
        return new Giveaway(PROMOTION_EVENT_AMOUNT <= orders.calculateTotalAmount());
    }

    public boolean isEligible() {
        return isEligible;
    }

    public int getAmount() {
        return Menu.CHAMPAGNE.getPrice();
    }

}
