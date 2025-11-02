package christmas.service;

import christmas.repository.MenuRepository;
import christmas.util.validator.IntegerValidator;
import christmas.util.validator.StringValidator;


public class OrderService {
    private final MenuService menuService;

    public OrderService(StringValidator stringValidator, IntegerValidator integerValidator) {
        this.menuService = new MenuService(new MenuRepository());
    }

}
