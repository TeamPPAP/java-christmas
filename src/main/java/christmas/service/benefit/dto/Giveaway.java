package christmas.service.benefit.dto;

import christmas.domain.value.Menu;

public record Giveaway(Menu giftMenu, int giftCount) {

    @Override
    public String toString() {
        return giftMenu.getMenuName() + " " + giftCount + "개";
    }
}
