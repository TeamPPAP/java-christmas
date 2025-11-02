package christmas.service;

import christmas.repository.MenuRepository;
import christmas.util.validator.IntegerValidator;
import christmas.util.validator.OrderValidator;
import christmas.util.validator.StringValidator;


public class OrderService {
    private final OrderValidator orderValidator;
    private final MenuService menuService;

    public OrderService(StringValidator stringValidator, IntegerValidator integerValidator) {
        this.orderValidator = new OrderValidator(stringValidator, integerValidator);
        this.menuService = new MenuService(new MenuRepository());
    }

}
