package christmas;

import christmas.controller.RestaurantController;
import christmas.repository.EventPlanRepository;
import christmas.repository.MenuRepository;
import christmas.service.DateService;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.util.validator.DateValidator;
import christmas.util.validator.EventValidator;
import christmas.util.validator.IntegerValidator;
import christmas.util.validator.StringValidator;
import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        IntegerValidator integerValidator = new IntegerValidator();
        StringValidator stringValidator = new StringValidator();
        EventPlanRepository eventPlanRepository = new EventPlanRepository();
        DateValidator dateValidator = new DateValidator(integerValidator);
        EventValidator eventValidator = new EventValidator(dateValidator);

        OrderService orderService = new OrderService(new MenuRepository(),new EventService());
        DateService dateService = new DateService(new EventPlanRepository());
        EventService eventService = new EventService(integerValidator,dateValidator,eventValidator,eventPlanRepository,orderService);

        RestaurantController controller = new RestaurantController(dateService,eventService,orderService);
    }
}
