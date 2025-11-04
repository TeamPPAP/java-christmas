package christmas.domain.model.message;

public enum ErrorMessage{
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.\n"),
    INVALID_DATE_RANGE("[ERROR] 유효하지 않은 날짜입니다. %d일와 %d일 사이에서 다시 입력해 주세요.\n"),
    INVALID_ORDER_FORMAT("[ERROR] 숫자로 입력해주세요.\n"),
    MENU_NOT_EXIST("[ERROR] 존재하지 않는 메뉴입니다.\n"),
    SELECT_CATEGORY_NUMBER("[ERROR] 올바른 카테고리 번호를 선택해주세요. (1:에피타이저, 2:메인, 3:디저트, 4:음료, 9:계산, 0:처음으로)\n"),
    DUPLICATE_MENU("[ERROR] %s는 이미 주문한 메뉴입니다. 다른 메뉴를 선택해주세요.\n"),
    NO_ORDER_ADDED("[ERROR] 주문이 추가되지 않았습니다. 양식에 맞게 작성 하셨는지 확인해주세요.\n"),
    EMPTY_ORDER_LIST("[ERROR] 주문 내역이 비어있습니다. 최소 1개 이상의 메뉴를 주문해주세요.\n"),
    DRINK_ONLY("[ERROR] 음료수만 주문할 수 없습니다.\n"),
    ORDER_QTY_ZERO("[ERROR] 메뉴 개수는 1 이상이어야 합니다.\n"),
    ORDER_LIMIT_EXCEEDED("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. (현재: %d개, 추가 시도: %d 개)\n"),
    EMPTY_INPUT("[ERROR] 빈값은 입력될 수 없습니다.\n"),
    INVALID_CHARACTER("[ERROR] 특수문자는 사용할 수 없습니다.\n"),
    NOT_COMPLETE_KOREAN("[ERROR] 완성된 한글 글자만 입력할 수 있습니다.\n"),
    MENU_PRICE_UNDER_ZERO("[ERROR] 메뉴 가격은 0원 이하로 입력 될 수 없습니다.\n"),
    MENU_CATEGORY_IS_BLANK("[ERROR] 메뉴의 카테고리가 입력되지 않았습니다.\n"),
    MENU_NAME_IS_BLANK("[ERROR] 메뉴 이름이 입력되지 않았습니다.\n"),
    INVALID_SELECT_RANGE("[ERROR] 유효하지 않은 날짜입니다. %d과 %d 사이의 숫자를 입력해 주세요.\n"),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
