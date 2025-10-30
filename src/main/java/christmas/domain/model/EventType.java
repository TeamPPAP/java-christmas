package christmas.domain.model;

public enum EventType {
    XMAS_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAYS_DISCOUNT("평일 할인"),
    WEEKENDS_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    GIVE_GIFT("증정 이벤트");

    public final String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
