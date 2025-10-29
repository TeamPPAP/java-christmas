package christmas.util.validator;

import java.util.List;

public class DateValidator {
    private final IntegerValidator integerValidator;
    private final int MAX_DAY = 31;
    private final int XMAS = 25;
    private final int MIN_DAY = 1;

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
        if (date < MIN_DAY || date > MAX_DAY) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 유효하지 않은 날짜입니다.%d일와 %d일 사이에서 다시 입력해 주세요.", MIN_DAY, MAX_DAY));
        }
    }
    public boolean isDateBeforeXmas(int date) {
        return date > MIN_DAY && date < XMAS;
    }
}