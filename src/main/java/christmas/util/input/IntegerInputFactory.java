package christmas.util.input;

import christmas.util.validator.IntegerValidator;
import christmas.view.InputView;

public class IntegerInputFactory {
    private final IntegerValidator validator;
    private final InputView inputView;

    public IntegerInputFactory(InputView inputView) {
        this.validator = new IntegerValidator();
        this.inputView = inputView;
    }

    public InputReader<Integer> createIntegerReader() {
        return new IntegerReader(inputView, validator);
    }
}
