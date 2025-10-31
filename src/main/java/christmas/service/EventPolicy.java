package christmas.service;

import christmas.domain.model.Orders;

public interface EventPolicy {
    boolean isSatisfy(Orders orders, int date);
    int discountAmount(Orders orders, int date);
    String eventVariety();
}
