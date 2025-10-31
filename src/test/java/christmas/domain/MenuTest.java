package christmas.domain;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void getMenuList() {
        Menu menu = new Menu();
        String menupan = menu.getMenuListByCategory();
        System.out.println(menupan);
        Assertions.assertTrue(menupan != null , "메뉴는 null일수 없음");

    }
}