package christmas.domain.order;

import christmas.domain.menu.Menu;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderParser {

    public Orders readOrders(String input) {
        Map<String, Integer> orderMap = parseOrders(input);

        List<Order> orders = orderMap.entrySet().stream()
                .map(entry -> new Order(Menu.from(entry.getKey()), entry.getValue()))
                .toList();

        return new Orders(orders);
    }

    private Map<String, Integer> parseOrders(String input) {
        validateInput(input);

        return Arrays.stream(input.split(","))
                .filter(item -> !item.isBlank())
                .map(this::parseOrderItem)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> {
                            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                        }
                ));
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private Map.Entry<String, Integer> parseOrderItem(String item) {
        String[] parts = item.split("-");
        validateOrderItemFormat(parts);

        String menuName = parts[0];
        int quantity = Integer.parseInt(parts[1]);
        validateQuantity(quantity);

        return Map.entry(menuName, quantity);
    }

    private void validateOrderItemFormat(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

}
