package christmas.util.validator;

import static christmas.domain.model.message.ErrorMessage.*;

public  class IntegerValidator {

    /**
     * 빈문자열, 널 체킹 및 문자열 int형 전환
     * @param input 입력문자열
     * @return Int
     * **/
    public int parseNotBlankInt(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    public void ensureInRange(int num, int min, int max) {
        if (num < min || num > max) {
            throw new IllegalArgumentException(
                    String.format(INVALID_DATE_RANGE.getMessage(), min, max)
            );
        }
    }

    public void ensureDigitsOnly(String str) {
        if (!str.matches("\\d+")) {
            throw new IllegalArgumentException("입력값은 부호(+, -) 없는 숫자만 포함해야 합니다.");
        }
    }
}
