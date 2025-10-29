package christmas.service.gift;

import christmas.domain.value.Menu;

import static christmas.domain.value.Menu.CHAMPAGNE;

public class GiveawayPromotion implements BenefitPolicy<Integer, String> {
    private final Menu giveaway = CHAMPAGNE;
    private final int threshold = 120_000;

    @Override
    public String apply(Integer totalPrice) {
        if (totalPrice >= threshold) {
            return giveaway.getMenuName() + " 1개";
        }
        return "없음";
    }
}

