package christmas.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void parseMenuList() {
        //given
        Order order = new Order("타파스-1,제로콜라-1, 제로콜라-1");
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