package christmas.domain;

import christmas.domain.value.Menu;

import static christmas.util.ObjectUtil.isDigit;
import static christmas.util.ObjectUtil.isNull;

public class OrderDetail {
    private Menu menu;
    private int cnt;

    public OrderDetail(String input) {
        String[] split = input.trim().split("-");

        validateCount(split[1]);
        this.menu = Menu.getByName(split[0]);
        this.cnt = Integer.parseInt(split[1]);
    }

    public OrderDetail(String menu, int cnt) {
        this.menu = Menu.getByName(menu);
        this.cnt = cnt;
    }

    public OrderDetail(Menu menu, int cnt) {
        if (isNull(menu)) {
            throw new IllegalArgumentException("menu is null");
        }

        if (cnt <= 0) {
            throw new IllegalArgumentException("cnt must be greater than 0");
        }

        this.menu = menu;
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

    private void validateCount(String input) {
        if (!isDigit(input)) {
            throw new IllegalArgumentException("알맞지 않은 개수입니다.");
        }
    }
}
