package christmas.service.benefit;

@FunctionalInterface
public interface BenefitPolicy<I, R> {

    R apply(I input);

}
