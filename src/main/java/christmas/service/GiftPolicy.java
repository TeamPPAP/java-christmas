package christmas.service;

import christmas.domain.model.Menu;
import christmas.domain.model.Order;

public interface GiftPolicy {
    boolean isSatisfy(Order order,int date);
    int discountAmount();
    Menu giftMenu();
}
