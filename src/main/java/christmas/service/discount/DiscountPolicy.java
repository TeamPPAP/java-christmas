package christmas.service.discount;

import christmas.service.discount.dto.DiscountResult;

public interface DiscountPolicy<T> {

    DiscountResult calculateDiscount(T type);

}
