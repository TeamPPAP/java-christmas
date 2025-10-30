package christmas.domain.model;

public class Menu {
    public final String menuName;
    public final int price;
    public final Category category;

    public Menu(String menuName, int price, Category category) {
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

}

