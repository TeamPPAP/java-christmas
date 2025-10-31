package christmas.domain.event.discount;

import christmas.domain.order.Order;
import christmas.domain.entity.VisitDate;

public class ChristmasEvent implements DiscountBenefit {
    final String TITLE = "크리스마스 디데이 할인";
    final int INITIAL_DISCOUNT = 1000;
    final int DAILY_INCREASE_AMOUNT = 100;
    private int eventPeriod = 0;
    private int discountPrice = 0;

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!isApplicable(visitDate, order))
            return ;

        setEventPeriod(visitDate);

        discount(order);
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Order order) {
        if(!DiscountBenefit.super.isApplicable(visitDate, order))
            return false;

        if(!visitDate.isBeforeChristmas())
            return false;

        return true;
    }

    private void setEventPeriod(VisitDate visitDate){
        this.eventPeriod = visitDate.getDayOfMonth() -1;
    }

    @Override
    public void discount(Order order) {
        this.discountPrice = INITIAL_DISCOUNT + (this.eventPeriod * DAILY_INCREASE_AMOUNT);
    }

    @Override
    public String toString() {
        return getBenefitSummary(TITLE, discountPrice);
    }
}
