package christmas.domain.model.message;

public enum ErrorMessage {
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_DATE_RANGE("[ERROR] 유효하지 않은 날짜입니다. %d일와 %d일 사이에서 다시 입력해 주세요."),
    INVALID_ORDER_FORMAT("[ERROR] 정해진 형식을 따라 입력해주세요."),
    MENU_NOT_EXIST("[ERROR] 존재하지 않는 메뉴입니다."),
    NO_ORDER_ADDED("[ERROR] 주문이 추가되지 않았습니다."),
    DUPLICATE_MENU("[ERROR] 이미 추가된 메뉴입니다."),
    DRINK_ONLY("[ERROR] 음료수만 주문할 수 없습니다."),
    ORDER_QTY_ZERO("[ERROR] 메뉴 개수는 0이 될 수 없습니다."),
    ORDER_LIMIT_EXCEEDED("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."),
    EMPTY_INPUT("[ERROR] 빈값은 입력될 수 없습니다."),
    INVALID_CHARACTER("[ERROR] 특수문자는 사용할 수 없습니다."),
    NOT_COMPLETE_KOREAN("[ERROR] 완성된 한글 글자만 입력할 수 있습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
