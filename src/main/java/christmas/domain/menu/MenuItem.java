package christmas.domain.menu;
public enum MenuItem {
    // APPETIZER
    MUSHROOM_SOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드",  8000, MenuCategory.APPETIZER),
    // MAIN
    T_BONE_STEAK("티본스테이크",  55000, MenuCategory.MAIN),
    BARBECUE_RIBS("바비큐립",  54000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타",  35000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타",  25000, MenuCategory.MAIN),
    // DESSERT
    CHOCOLATE_CAKE("초코케이크",  15000, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림",  5000, MenuCategory.DESSERT),
    // BEVERAGE
    ZERO_COLA("제로콜라",  3000, MenuCategory.DRINK),
    RED_WINE("레드와인", 60000, MenuCategory.DRINK),
    CHAMPAGNE("샴페인",  25000, MenuCategory.DRINK);

    private final String title;
    private final int price;
    private final MenuCategory category;

    MenuItem(String title,  int price, MenuCategory category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }
    public int getPrice() { return price; }
    public MenuCategory getCategory() { return category; }

    public static MenuItem of(String title){
        for(MenuItem item : MenuItem.values()){
            if(item.getTitle().equals(title))
                return item;
        }

        throw new IllegalArgumentException("존재하지 않는 메뉴입니다. : " + title);
    }

    @Override
    public String toString() {
        return title + "(" + formatPrice() +")";
    }

    private String formatPrice(){
        return String.format("%,d", this.price);
    }
}

