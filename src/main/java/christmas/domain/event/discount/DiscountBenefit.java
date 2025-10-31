package christmas.domain.event.discount;

import christmas.domain.order.Order;
import christmas.domain.event.Benefit;

public interface DiscountBenefit extends Benefit {
    void discount(Order order);

    default String getBenefitSummary(String title, int discountPrice){
        return String.format("%s: -%d원", title, discountPrice);
    }

}
