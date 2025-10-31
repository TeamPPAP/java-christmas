package christmas.service;

import christmas.domain.model.constant.Category;
import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import christmas.repository.EventPlanRepository;
import christmas.util.validator.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EventServiceTest {
    StringValidator stringValidator = new StringValidator();
    IntegerValidator integerValidator = new IntegerValidator();
    DateValidator dateValidator = new DateValidator(new IntegerValidator());
    EventValidator eventValidator = new EventValidator();
    List<Order> orderList = new ArrayList<>();
    OrderService orderService = new OrderService(stringValidator,integerValidator);

    public void init() {
        Menu menu = new Menu("스테이크",80000, Category.MAIN_DISH);
        Menu menu2 = new Menu("스테이크2",200, Category.MAIN_DISH);
        Menu menu3 = new Menu("아이스크림",100, Category.DESSERT);
        orderList.add(new Order(menu,1));
        orderList.add(new Order(menu2,2));
        orderList.add(new Order(menu3,5));
    }

    @Test
    void isCalAmountForEvent() {
        init();
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