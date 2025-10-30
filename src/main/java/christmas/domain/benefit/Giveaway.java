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

    public Menu getMenu() {
        if (isEligible) {
            return Menu.CHAMPAGNE;
        }
        return Menu.NONE;
    }

    public int getPrice() {
        if (isEligible) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return Menu.NONE.getPrice();
    }

    public int getQuantity() {
        if (isEligible) {
            return 1;
        }
        return 0;
    }

    public String getBenefitName() {
        return "증정 이벤트";
    }

}
