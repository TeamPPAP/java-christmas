package christmas.service;

import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import christmas.repository.MenuRepository;
import christmas.util.validator.IntegerValidator;
import christmas.util.validator.OrderValidator;
import christmas.util.validator.StringValidator;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderValidator orderValidator;
    private final MenuService menuService;

    public OrderService(StringValidator stringValidator, IntegerValidator integerValidator) {
        this.orderValidator = new OrderValidator(stringValidator, integerValidator);
        this.menuService = new MenuService(new MenuRepository());
    }

    public int totalOrderPrice(List<Order> orders) {
        return orders.stream().mapToInt(order ->
                order.getOnlyOrderPrice() * order.getQuantity()).sum();
    }

    public List<String> splitOrderSentence(String order) throws IllegalArgumentException {
        List<String> orders = Arrays.stream(order.split(",")).toList();
        orderValidator.matchOrderPattern(orders);
        return orders;
    }

    public List<Order> convertStringToMenu(List<String> orderList) {
        Map<String, Menu> menuMap = menuService.getAllMenu().stream()
                .collect(Collectors.toMap(Menu::getMenuName, Function.identity()));
        return orderList.stream().map(order -> createOrderFrom(order, menuMap)).toList();
    }

    private Order createOrderFrom(String orderString, Map<String, Menu> menuMap) {
        String[] parts = orderString.split("-");

        String menuName = parts[0];
        Menu menu = menuMap.get(menuName);

        int quantity = Integer.parseInt(parts[1]);
        return new Order(menu, quantity);
    }

    public List<Order> confirmVerifiedOrder(List<String> orderList) throws IllegalArgumentException {
        List<Order> orders = convertStringToMenu(orderList);

        orderValidator.isOderListEmpty(orders);
        orderValidator.existMenuName(orders,menuService.getAllMenu());
        orderValidator.onlyOrderDrink(orders);
        orderValidator.orderQuantityLeastOne(orderList);
        orderValidator.totalCountWithinLimit(orderList);

        return orders;
    }
}
