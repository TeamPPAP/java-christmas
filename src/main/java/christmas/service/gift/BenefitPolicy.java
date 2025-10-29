package christmas.service.gift;

@FunctionalInterface
public interface BenefitPolicy<I, R> {

    R apply(I input);

}
