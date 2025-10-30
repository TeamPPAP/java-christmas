package christmas.service.discount.dto;

import static christmas.util.ObjectUtil.formatPrice;

public record DiscountResult(String benefitName, int discountAmount) {

    @Override
    public String toString() {
        return benefitName + ": -" + formatPrice(discountAmount);
    }

    public static DiscountResult to(String benefitName, int discountAmount) {
        if (discountAmount == 0) {
            return null;
        }
        return new DiscountResult(benefitName, discountAmount);
    }

}
