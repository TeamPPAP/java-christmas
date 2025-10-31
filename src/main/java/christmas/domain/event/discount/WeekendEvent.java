package christmas.domain.event.discount;

import christmas.domain.order.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.menu.MenuCategory;

public class WeekendEvent implements DiscountBenefit {
    final String TITLE = "주말 할인";
    final int DAILY_INCREASE_AMOUNT = 2025;
    int discountPrice = 0;

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!isApplicable(visitDate, order))
            return;

        // 2. 주말할인 체크 visitDate.isWeekend
        if(visitDate.isWeekDay())
            return;

        discount(order);
    }

    @Override
    public void discount(Order order) {
        discountPrice = order.getCountByCategory(MenuCategory.MAIN) * DAILY_INCREASE_AMOUNT;
    }

    @Override
    public String toString() {
        return getBenefitSummary(TITLE, discountPrice);
    }
}
