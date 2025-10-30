package christmas.domain.event;

import christmas.domain.entity.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.enums.MenuCategory;

import java.time.LocalDate;

public class WeekDayEvent extends Discountable {
    final int DAILY_INCREASE_AMOUNT = 2025;

    protected WeekDayEvent() {
        super(EventType.DISCOUNT, LocalDate.of(2025, 12, 31));
    }

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!super.isApplicable(visitDate, order))
            return;

        if(visitDate.isWeekend())
            return;

        discount(order);
    }

    @Override
    void discount(Order order) {
        // 3. 디저트 메뉴 갯수(Order객체에서 디저트 메뉴 갯수 리턴받는 메서드 작성필요) * 2025원
        super.setDiscountPrice(order.getCountByCategory(MenuCategory.DESSERT) * DAILY_INCREASE_AMOUNT);
    }

    @Override
    public String toString() {
        return "평일 할인: -" + super.discountPrice + "원";
    }
}
