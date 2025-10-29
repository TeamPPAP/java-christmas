package christmas.service;

import christmas.domain.model.Category;
import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import christmas.util.input.IntegerReader;
import christmas.util.validator.EventValidator;
import christmas.util.validator.IntegerValidator;
import christmas.util.validator.OrderValidator;
import christmas.util.validator.StringValidator;
import christmas.view.InputView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {
    EventService service = new EventService();
    List<Order> orderList = new ArrayList<>();

    public void init() {
        Menu menu = new Menu("스테이크",8000, Category.MAIN_DISH);
        Menu menu2 = new Menu("스테이크2",28000, Category.MAIN_DISH);
        Menu menu3 = new Menu("아이스크림",70000, Category.DESSERT);
        orderList.add(new Order(menu,1));
        orderList.add(new Order(menu2,2));
        orderList.add(new Order(menu3,5));
    }

    @Test
    void isCalAmountForEvent() {
        init();
        System.out.println(service.isCalAmountForGift(orderList));
    }

    @Test
    void dDayDiscount() {
    }

    @Test
    void weekDiscount() {
    }

    @Test
    void specialDiscount() {
    }

    @Test
    void isCalAmountForGift() {
    }
}