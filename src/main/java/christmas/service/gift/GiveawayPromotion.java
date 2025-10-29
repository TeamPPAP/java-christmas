package christmas.service.gift;

import christmas.domain.value.Menu;

import static christmas.domain.value.Menu.CHAMPAGNE;

public class GiveawayPromotion implements BenefitPolicy<Integer, String> {
    private final Menu giveaway = CHAMPAGNE;
    private static final int THRESHOLD = 120_000;

    @Override
    public String apply(Integer totalPrice) {
        if (totalPrice < THRESHOLD) {
            return "없음";
        }
        return giveaway.getMenuName() + " 1개";
    }
}

