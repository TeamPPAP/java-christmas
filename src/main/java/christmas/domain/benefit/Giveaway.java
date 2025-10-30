package christmas.domain.benefit;

import christmas.domain.menu.Menu;
import christmas.domain.order.Orders;

public class Giveaway {

    private static final int PROMOTION_EVENT_AMOUNT = 120_000;
    private final boolean isEligible;
    private final int quantity = 1;

    private Giveaway(boolean isEligible) {
        this.isEligible = isEligible;
    }

    public static Giveaway from(Orders orders) {
        return new Giveaway(PROMOTION_EVENT_AMOUNT <= orders.calculateTotalAmount());
    }

    public String getMenu() {
        if (isEligible) {
            return Menu.CHAMPAGNE.getName();
        }
        return Menu.NONE.getName();
    }

    public int getPrice() {
        if (isEligible) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return Menu.NONE.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBenefitName() {
        return "증정 이벤트";
    }

}
