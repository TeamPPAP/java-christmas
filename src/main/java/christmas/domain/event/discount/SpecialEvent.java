package christmas.domain.event.discount;

import christmas.domain.order.Order;
import christmas.domain.entity.VisitDate;


public class SpecialEvent implements DiscountBenefit {
    final String TITLE = "특별할인";
    int dicountPrice = 0;

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!isApplicable(visitDate, order))
            return;

        if(visitDate.isSpecialDay())
            discount(order);

    }

    @Override
    public void discount(Order order) {
        this.dicountPrice = 1000;
    }

    @Override
    public String toString() {
        return getBenefitSummary(TITLE, dicountPrice);
    }
}
