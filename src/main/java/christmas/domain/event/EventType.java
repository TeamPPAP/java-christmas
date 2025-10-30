package christmas.domain.event;

public enum EventType {
    DISCOUNT("할인"), // 필요한 필드가 할인금액
    GIFT("증정");     // 공통적으로 필요한 필드가 샴페인 add 메서드가 필요??

    private final String title;

    EventType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
