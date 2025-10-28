package christmas.domain.menu;

import java.util.Arrays;

public enum Menu {
    
    MUSHROOM_SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuType.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN),
    BBQ_RIBS("바비큐립", 54_000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),

    ZERO_COLA("제로콜라", 3_000, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60_000, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, MenuType.BEVERAGE);

    private final String name;
    private final int price;
    private final MenuType type;

    Menu(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Menu from(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }

}
