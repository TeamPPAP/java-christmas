package christmas.domain.event;

import christmas.domain.entity.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.enums.MenuItem;

import java.time.LocalDate;

public class GiftEvent extends Giftable {

    public GiftEvent() {
        super(EventType.GIFT, LocalDate.of(2025,12,31));
    }

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!super.isApplicable(visitDate, order))
            return;

        if(order.getTotalPrice() < 120000)
            return;

        present(order);

    }

    @Override
    public void present(Order order) {
        super.gift = MenuItem.CHAMPAGNE;
        super.giftCount = 1;

        order.addOrder(this.gift, this.giftCount);
    }

    @Override
    public String toString() {
        if(this.giftCount == 0)
            return "증정 이벤트: " + "없음";
        return "증정 이벤트: " + super.gift + " " + this.giftCount + "개";
    }
}
