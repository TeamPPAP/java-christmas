package christmas.service.gift;

@FunctionalInterface
public interface BenefitPolicy<T, R> {

    R apply(T type);

}
