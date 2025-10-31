package christmas.util.validator;

public  class IntegerValidator {

    /**
     * 빈문자열, 널 체킹 및 문자열 int형 전환
     * @param input 입력문자열
     * @return Int
     * **/
    public int parseNotBlankInt(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈값은 입력될 수 없습니다.");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자여야 합니다.");
        }
    }

    public void ensureInRange(int num, int min, int max) {
        if (num < min || num > max) {
            throw new IllegalArgumentException(
                    String.format("숫자는 %d와 %d 사이여야 합니다.", min, max)
            );
        }
    }

    public void ensurePositive(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("숫자는 0보다 커야 합니다.");
        }
    }

    public void ensureDigitsOnly(String str) {
        if (!str.matches("\\d+")) {
            throw new IllegalArgumentException("입력값은 부호(+, -) 없는 숫자만 포함해야 합니다.");
        }
    }
}
