package christmas.controller;

import christmas.service.DateService;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.util.input.InputFactory;
import christmas.util.input.InputReader;
import christmas.view.InputView;

public class RestaurantController {
    private final InputFactory inputFactory = new InputFactory(new InputView());
    private final InputReader<Integer> integerReader = inputFactory.createIntegerReader();
    private final InputReader<String> stringReader = inputFactory.createStringReader();

    private final DateService dateService;
    private final EventService eventService;
    private final OrderService orderService;

    public RestaurantController(DateService dateService, EventService eventService, OrderService orderService) {
        this.dateService = dateService;
        this.eventService = eventService;
        this.orderService = orderService;
    }


}
