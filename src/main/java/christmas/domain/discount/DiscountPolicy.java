package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;

public interface DiscountPolicy {

    int DISCOUNT_AMOUNT = 2_025;

    int calculateDiscount(VisitDate visitDate, Orders orders);

    boolean isApplicable(VisitDate visitDate, Orders orders);

    String getDiscountName();

}
