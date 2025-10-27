package christmas.util.validator;

public class DateValidator {
    private final IntegerValidator integerValidator;

    public DateValidator(IntegerValidator integerValidator) {
        this.integerValidator = integerValidator;
    }

    public int validateVisitDate(String input) {
        int date;
        try {
            date = integerValidator.parseNotBlankInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }

        validateDateRange(date);
        return date;
    }

    private void validateDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}