package christmas.util.input;

import christmas.util.validator.StringValidator;
import christmas.view.InputView;

public class StringInputFactory {
    private final StringValidator validator;
    private final InputView inputView;

    public StringInputFactory(InputView inputView) {
        this.validator = new StringValidator();
        this.inputView = inputView;
    }

    public InputReader<String> createStringReader() {
        return new StringReader(inputView, validator);
    }
}
