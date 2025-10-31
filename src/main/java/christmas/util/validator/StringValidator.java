package christmas.util.validator;
import static christmas.domain.model.message.ErrorMessage.*;
public class StringValidator {

    /**
     * 빈문자열, 널 체킹 및 문자열 int형 전환
     * @param input 입력문자열
     * @return String
     * **/
    public String notBlankString(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
        return input.trim();
    }

    public void notSpecialCharacterString(String input) {
        if (!input.matches("^[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9\\s]+$")) {
            throw new IllegalArgumentException(INVALID_CHARACTER.getMessage());        }
    }
    public void ensureCompleteString(String input) {
        if (input.matches("^[ㄱ-ㅎ]+$")) {
            throw new IllegalArgumentException(NOT_COMPLETE_KOREAN.getMessage());
        }
    }
}
