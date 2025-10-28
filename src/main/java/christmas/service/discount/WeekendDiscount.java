package christmas.service.discount;

public class WeekendDiscount implements DiscountPolicy {

    @Override
    public int calculateDiscount() {
        return 0;
    }
}
