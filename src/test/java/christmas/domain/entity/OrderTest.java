package christmas.domain.entity;

import christmas.domain.menu.MenuItem;
import christmas.domain.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void parseMenuList() {
        //given
        Order order = new Order("타파스-1,제로콜라-1, 제로콜라-1");
        order.addOrder(MenuItem.TAPAS, 4);
        System.out.println(order.toString());
    }

    @Test
    void getTotalMenuPrice() {
        //given
        Order order = new Order("타파스-1,제로콜라-1");

        int totalPrice = order.getTotalPrice();
        Assertions.assertTrue(totalPrice > 0, totalPrice + " 원");
    }


}