package christmas.service.discount.dto;

import static christmas.util.ObjectUtil.formatPrice;

public record DiscountResult(String benefitName, int discountAmount) {

    @Override
    public String toString() {
        return benefitName + ": - " + formatPrice(discountAmount) + "원";
    }

}
