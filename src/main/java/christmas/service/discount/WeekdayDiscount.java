package christmas.service.discount;

public class WeekdayDiscount implements DiscountPolicy<Object> {
    @Override
    public int calculateDiscount(Object o) {
        return 0;
    }
}
