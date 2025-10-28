package christmas.util.input;

import christmas.util.validator.StringValidator;
import christmas.view.InputView;

public class StringReader implements InputReader<String> {
    private final InputView inputView;
    private final StringValidator validator;

    public StringReader(InputView inputView, StringValidator validator) {
        this.inputView = inputView;
        this.validator = validator;
    }

    @Override
    public String read() throws IllegalArgumentException {
        return validator.notBlankString(inputView.readLine());
    }
}