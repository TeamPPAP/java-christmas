package christmas.domain;

import christmas.domain.value.Menu;

public class OrderDetail {
    private Menu menu;
    private int cnt;

    public OrderDetail(String menu, int cnt) {
        if (cnt <= 0) {
            throw new IllegalArgumentException("cnt must be greater than 0");
        }

        this.menu = Menu.getByName(menu);
        this.cnt = cnt;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCnt() {
        return cnt;
    }

    public int getTotalPrice() {
        return menu.getPrice() * cnt;
    }

    @Override
    public String toString() {
        return menu.getMenuName() + " " + cnt + "개";
    }

}
