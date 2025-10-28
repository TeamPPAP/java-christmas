package christmas.domain.value;

public enum MenuType {

    APPETIZER("에피타이저"),
    MAIN_MENU("메인"),
    DESSERT("디저트"),
    DRINKS("음료");

    private final String typeName;

    MenuType(String typeName) {
        this.typeName = typeName;
    }
}
