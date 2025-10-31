package christmas.util.input;

import christmas.util.validator.IntegerValidator;
import christmas.util.validator.StringValidator;
import christmas.view.InputView;

public class InputFactory {
    private final IntegerValidator integervalidator;
    private final StringValidator stringValidator;
    private final InputView inputView;

    public InputFactory(InputView inputView) {
        this.integervalidator = new IntegerValidator();
        this.stringValidator = new StringValidator();
        this.inputView = inputView;
    }

    public InputReader<Integer> createIntegerReader() {
        return new IntegerReader(inputView, integervalidator);
    }
    public InputReader<String> createStringReader() {
        return new StringReader(inputView, stringValidator);
    }
}
