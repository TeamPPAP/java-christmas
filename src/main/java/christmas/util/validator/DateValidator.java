package christmas.util.validator;
import static christmas.domain.model.message.ErrorMessage.*;
import static christmas.domain.model.constant.FinalConstant.*;

public class DateValidator {
    private final IntegerValidator integerValidator;

    public DateValidator(IntegerValidator integerValidator) {
        this.integerValidator = integerValidator;
    }

    public int validateVisitDate(String input) {
        int date;
        try {
            date = integerValidator.parseNotBlankInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
        validateDateRange(date);
        return date;
    }

    private void validateDateRange(int date) {
        if (date < MIN_DAY.get() || date > MAX_DAY.get()) {
            throw new IllegalArgumentException(String.format(INVALID_DATE_RANGE.getMessage(),MIN_DAY.get(), MAX_DAY.get()));
        }
    }
}
