package christmas.service;

import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import christmas.domain.model.OrderLine;
import christmas.repository.MenuRepository;
import christmas.util.validator.IntegerValidator;
import christmas.util.validator.OrderValidator;
import christmas.util.validator.StringValidator;

import java.util.*;
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
                order.getOrderPrice() * order.getQuantity()).sum();
    }

    public List<String> splitOrderSentence(String order) throws IllegalArgumentException {
        List<String> orders = Arrays.stream(order.split(",")).toList();
        orderValidator.matchOrderPattern(orders);
        return orders;
    }

    public List<Order> convertToOrders(List<OrderLine> merged) {
        Map<String, Menu> menuMap = menuService.getAllMenu().stream()
                .collect(Collectors.toMap(Menu::getMenuName, Function.identity()));
        return merged.stream().map(order -> createOrderFrom(order, menuMap)).toList();
    }

    private Order createOrderFrom(OrderLine order, Map<String, Menu> menuMap) {
        String menuName = order.getName();
        Menu menu = menuMap.get(menuName);

        int quantity = order.getQty();
        return new Order(menu, quantity);
    }

    public List<Order> confirmVerifiedOrder(List<String> orderList) throws IllegalArgumentException {
        List<OrderLine> lines = parseOrderLines(orderList);

        List<OrderLine> merged = mergeSameMenu(lines);

        List<Order> orders = convertToOrders(merged);

        orderValidator.isOderListEmpty(orders);
        orderValidator.existMenuName(orders,menuService.getAllMenu());
        orderValidator.onlyOrderDrink(orders);
        orderValidator.orderQuantityLeastOne(orderList);
        orderValidator.totalCountWithinLimit(orderList);

        return orders;
    }

    private List<OrderLine> parseOrderLines(List<String> orderList) {
        List<OrderLine> lines = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            String[] parts = orderList.get(i).split("-");
            lines.add(new OrderLine(parts[0], Integer.parseInt(parts[1]))); //add 횟수 == result.size()
        }
        return lines;
    }

    private List<OrderLine> mergeSameMenu(List<OrderLine> lines) {
        Map<String, Integer> menuToQty = new LinkedHashMap<>();
        for (OrderLine line : lines) {
            String menuName = line.getName();
            int qty = line.getQty();
            menuToQty.merge(menuName, qty, Integer::sum);
        }
        List<OrderLine> merged = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : menuToQty.entrySet()) {
            merged.add(new OrderLine(entry.getKey(), entry.getValue()));
        }
        return merged;
    }
}
