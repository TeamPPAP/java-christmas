package christmas.domain.value;

import java.util.Arrays;

import static christmas.domain.value.MenuType.APPETIZER;
import static christmas.domain.value.MenuType.DESSERT;
import static christmas.domain.value.MenuType.DRINKS;
import static christmas.domain.value.MenuType.MAIN_MENU;
import static christmas.util.ObjectUtil.isBlank;

public enum Menu {

    BUTTON_MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, MAIN_MENU),
    BARBECUE_RIBS("바비큐립", 54_000, MAIN_MENU),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN_MENU),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN_MENU),

    CHOCOLATE_CATE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5_000, DESSERT),

    ZERO_COLA("제로콜라", 3_000, DRINKS),
    RED_WINE("레드와인", 60_000, DRINKS),
    CHAMPAGNE("샴페인", 25_000, DRINKS);

    private final String menuName;
    private final int price;
    private final MenuType menuType;

    Menu(String menuName, int price, MenuType menuType) {
        this.menuName = menuName;
        this.price = price;
        this.menuType = menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public static Menu getByName(String menuName) {
        if (isBlank(menuName)) {
            throw new IllegalArgumentException("menuName is blank");
        }

        return Arrays.stream(values())
            .filter(m -> m.menuName.equals(menuName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴 이름입니다: " + menuName));
    }
}
