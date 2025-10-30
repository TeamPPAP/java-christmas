package christmas.domain.event;

import christmas.domain.entity.Order;

import java.time.LocalDate;

public abstract class Discountable extends EventDetail  {
    int discountPrice = 0;

    protected void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }



    protected Discountable(EventType eventType, LocalDate endDate) {
        super(eventType, endDate);
    }

    abstract void discount(Order order);
}
