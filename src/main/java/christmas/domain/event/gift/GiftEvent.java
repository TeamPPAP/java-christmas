package christmas.domain.event.gift;

import christmas.domain.order.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.menu.MenuItem;

public class GiftEvent implements GiftBenefit {

    private String TITLE = "증정 이벤트";
    private MenuItem gift;
    private int giftCount = 0;
    private final int MINIMUM_AMOUNT_FOR_GIFT = 120000;


    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!isApplicable(visitDate, order))
            return;

        if(order.getTotalPrice() < MINIMUM_AMOUNT_FOR_GIFT)
            return;

        present(order);
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Order order) {
        return GiftBenefit.super.isApplicable(visitDate, order);
    }

    @Override
    public void present(Order order) {
        gift = MenuItem.CHAMPAGNE;
        giftCount += 1;
    }

    @Override
    public String toString() {
        return getBenefitSummary(TITLE, gift, giftCount);
    }
}
