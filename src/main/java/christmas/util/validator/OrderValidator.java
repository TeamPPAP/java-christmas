package christmas.util.validator;

public class OrderValidator {
    private static final int MAX_TOTAL_COUNT = 20;
    private final StringValidator stringValidator;
    private final IntegerValidator integerValidator;

    public OrderValidator(StringValidator stringValidator, IntegerValidator integerValidator) {
        this.integerValidator = integerValidator;
        this.stringValidator = stringValidator;
    }


}
