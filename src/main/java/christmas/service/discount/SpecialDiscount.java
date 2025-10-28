package christmas.service.discount;

public class SpecialDiscount implements DiscountPolicy<Object> {
    @Override
    public int calculateDiscount(Object o) {
        return 0;
    }
}
