package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

public class PromotionDiscount implements DiscountPolicy {

    // 샴페인 프로모션 전용 할인
    @Override
    public int calculateDiscount(VisitDate visitDate, Orders orders) {
        // TODO
        return 0;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        // TODO
        return false;
    }

    @Override
    public String getDiscountName() {
        return "증정 이벤트";
    }

}
