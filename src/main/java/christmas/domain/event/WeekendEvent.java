package christmas.domain.event;

import christmas.domain.entity.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.enums.MenuCategory;

import java.time.LocalDate;

public class WeekendEvent extends Discountable{
    final int DAILY_INCREASE_AMOUNT = 2025;

    protected WeekendEvent() {
        super(EventType.DISCOUNT, LocalDate.of(2025, 12, 31));
    }

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!super.isApplicable(visitDate, order))
            return;

        // 2. 주말할인 체크 visitDate.isWeekend
        if(visitDate.isWeekDay())
            return;

        discount(order);
    }

    @Override
    void discount(Order order) {
        super.setDiscountPrice(order.getCountByCategory(MenuCategory.MAIN) * DAILY_INCREASE_AMOUNT);
    }

    @Override
    public String toString() {
        return "주말 할인: -" + super.discountPrice + "원";
    }
}
