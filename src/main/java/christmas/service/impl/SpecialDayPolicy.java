package christmas.service.impl;

import christmas.domain.model.Orders;
import christmas.service.EventPolicy;

public class SpecialDayPolicy implements EventPolicy {
    @Override
    public boolean isSatisfy(Orders orders, int date) {
        return false;
    }

    @Override
    public int discountAmount(Orders orders, int date) {
        return 0;
    }

    @Override
    public String eventVariety() {
        return "";
    }
}
