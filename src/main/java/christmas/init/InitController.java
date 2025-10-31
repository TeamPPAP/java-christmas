package christmas.init;

import christmas.controller.RestaurantController;
import christmas.repository.EventPlanRepository;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.util.validator.DateValidator;
import christmas.util.validator.EventValidator;
import christmas.util.validator.IntegerValidator;
import christmas.util.validator.StringValidator;

public class InitController {
    IntegerValidator integerValidator = new IntegerValidator();
    StringValidator stringValidator = new StringValidator();
    EventPlanRepository eventPlanRepository = new EventPlanRepository();
    DateValidator dateValidator = new DateValidator(integerValidator);
    EventValidator eventValidator = new EventValidator(dateValidator);

    OrderService orderService = new OrderService(stringValidator,integerValidator);
    EventService eventService = new EventService(eventValidator,eventPlanRepository,orderService);

    public RestaurantController forCreateController(){
        return new RestaurantController(eventService,orderService,integerValidator);
    }
}
