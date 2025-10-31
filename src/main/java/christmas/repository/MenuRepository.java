package christmas.repository;

import christmas.domain.model.Category;
import christmas.domain.model.Menu;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    public MenuRepository() {
    }

    public List<Menu> getMenuList() {
        List<Menu> menusFromDB = new ArrayList<>();

        // <애피타이저>
        menusFromDB.add(new Menu("양송이수프", 6000, Category.APPETIZER));
        menusFromDB.add(new Menu("타파스", 5500, Category.APPETIZER));
        menusFromDB.add(new Menu("시저샐러드", 8000, Category.APPETIZER));

        // <메인>
        menusFromDB.add(new Menu("티본스테이크", 55000, Category.MAIN_DISH));
        menusFromDB.add(new Menu("바비큐립", 54000, Category.MAIN_DISH));
        menusFromDB.add(new Menu("해산물파스타", 35000, Category.MAIN_DISH));
        menusFromDB.add(new Menu("크리스마스파스타", 25000, Category.MAIN_DISH));

        // <디저트>
        menusFromDB.add(new Menu("초코케이크", 15000, Category.DESSERT));
        menusFromDB.add(new Menu("아이스크림", 5000, Category.DESSERT));

        // <음료>
        menusFromDB.add(new Menu("제로콜라", 3000, Category.DRINK));
        menusFromDB.add(new Menu("레드와인", 60000, Category.DRINK));
        menusFromDB.add(new Menu("샴페인", 25000, Category.DRINK));

        return menusFromDB;
    }
}
