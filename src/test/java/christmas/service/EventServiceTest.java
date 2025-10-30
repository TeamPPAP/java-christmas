package christmas.service;

import christmas.domain.model.Category;
import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import christmas.repository.EventPlanRepository;
import christmas.repository.MenuRepository;
import christmas.util.input.IntegerReader;
import christmas.util.validator.*;
import christmas.view.InputView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {
    DateValidator dateValidator = new DateValidator(new IntegerValidator());
    EventValidator eventValidator = new EventValidator(dateValidator);
    EventService service = new EventService(eventValidator,new EventPlanRepository(),new OrderService());
    List<Order> orderList = new ArrayList<>();
    OrderService orderService = new OrderService();

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
        System.out.println(service.isCalAmountForEvent(orderList));
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