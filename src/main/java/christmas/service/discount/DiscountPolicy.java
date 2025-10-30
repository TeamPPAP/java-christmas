package christmas.service.discount;

import christmas.service.DiscountContext;
import christmas.service.discount.dto.DiscountResult;

public interface DiscountPolicy {

    DiscountResult calculateDiscount(DiscountContext context);

}
