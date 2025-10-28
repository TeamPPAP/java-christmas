package christmas.service.discount;

public interface DiscountPolicy<T> {

    int calculateDiscount(T type);

}
