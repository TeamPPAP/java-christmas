package christmas.service.discount;

public class WeekendDiscount implements DiscountPolicy<Object> {

    @Override
    public int calculateDiscount(Object o) {
        return 0;
    }
}
