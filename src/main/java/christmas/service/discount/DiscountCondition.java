package christmas.service.discount;

@FunctionalInterface
public interface DiscountCondition<T> {
    int DISCOUNT_PER_MENU = 2025;

    boolean isSatisfiedBy(T type);
}
