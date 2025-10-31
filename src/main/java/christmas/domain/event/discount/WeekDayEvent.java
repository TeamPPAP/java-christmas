package christmas.domain.event.discount;

import christmas.domain.order.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.menu.MenuCategory;

public class WeekDayEvent implements DiscountBenefit {

    final String TITLE = "주중 할인";
    final int DAILY_INCREASE_AMOUNT = 2025;
    int discountPrice = 0;

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!isApplicable(visitDate, order))
            return;

        if(visitDate.isWeekend())
            return;

        discount(order);
    }

    @Override
    public void discount(Order order) {
        this.discountPrice = order.getCountByCategory(MenuCategory.DESSERT) * DAILY_INCREASE_AMOUNT;
    }

    @Override
    public String toString() {
        return getBenefitSummary(TITLE,this.discountPrice);
    }
}
