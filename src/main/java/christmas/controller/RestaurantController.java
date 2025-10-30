package christmas.controller;

import christmas.domain.model.Badge;
import christmas.service.DateService;
import christmas.service.EventService;
import christmas.service.OrderService;

public class RestaurantController {
    private final DateService dateService;
    private final EventService eventService;
    private final OrderService orderService;

    public RestaurantController(DateService dateService, EventService eventService, OrderService orderService) {
        this.dateService = dateService;
        this.eventService = eventService;
        this.orderService = orderService;
    }


}
