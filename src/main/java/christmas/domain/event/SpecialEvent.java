package christmas.domain.event;

import christmas.domain.entity.Order;
import christmas.domain.entity.VisitDate;

import java.time.LocalDate;

public class SpecialEvent extends Discountable {
    protected SpecialEvent() {
        super(EventType.DISCOUNT, LocalDate.of(2025, 12, 31));
    }

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!super.isApplicable(visitDate, order))
            return;

        // 4. visitdate.isSpecialDay 이걸 체크해서 true면 총금액에서 1000원 할인 아니면 0원 할인
        if(visitDate.isSpecialDay())
            discount(order);

    }

    @Override
    void discount(Order order) {
        super.setDiscountPrice(1000);
    }

    @Override
    public String toString() {
        return "특별 할인: -" + super.discountPrice + "원";
    }
}
