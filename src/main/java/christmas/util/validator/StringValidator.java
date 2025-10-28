package christmas.util.validator;

public class StringValidator {

    /**
     * 빈문자열, 널 체킹 및 문자열 int형 전환
     * @param input 입력문자열
     * @return String
     * **/
    public String notBlankString(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("빈값은 입력될 수 없습니다.");
        }
        return input.trim();
    }

    public void notSpecialCharacterString(String input) {
        if (!input.matches("^[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9\\s]+$")) {
            throw new IllegalArgumentException("특수문자는 사용할 수 없습니다.");        }
    }
    public void ensureCompleteString(String input) {
        if (input.matches("^[ㄱ-ㅎ]+$")) {
            throw new IllegalArgumentException("완성된 한글 글자만 입력할 수 있습니다");
        }
    }
}
