package christmas.domain.enums;

import christmas.domain.menu.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MenuItemTest {

    @Test
    void testToString() {
        // given
        MenuItem menuItem = MenuItem.CHAMPAGNE;
        //when
        String menuRow = menuItem.toString();
        // then
        Assertions.assertTrue(menuRow != null , "메뉴소개라인은 null일수 없음");

    }
}