package christmas.util.input;

import christmas.util.validator.IntegerValidator;
import christmas.view.InputView;

public class IntegerReader implements InputReader<Integer> {

    private final IntegerValidator validator;
    private final InputView inputView;

    public IntegerReader(InputView inputView, IntegerValidator validator) {
        this.inputView = inputView;
        this.validator = validator;
    }

    @Override
    public Integer read() throws IllegalArgumentException {
        String input = inputView.readLine();
        return validator.parseNotBlankInt(input);
    }
}