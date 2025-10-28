package christmas.controller;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.ChristmasDDayDiscount;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.Discounts;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final List<DiscountPolicy> discountPolicies;

    public EventPlannerController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.discountPolicies = List.of(
                new ChristmasDDayDiscount(),
                new WeekdayDiscount(),
                new WeekendDiscount(),
                new SpecialDiscount()
        );
    }

    public void run() {
        // TODO 입력
        // TODO 계산
        // TODO 출력
    }

    public Orders readOrders() {
        String input = inputView.readOrders();

        Map<String, Integer> orderMap = parseOrders(input);

        List<Order> orders = orderMap.entrySet().stream()
                .map(entry -> new Order(Menu.from(entry.getKey()), entry.getValue()))
                .toList();

        return new Orders(orders);
    }

    private Discounts calculateDiscounts(VisitDate visitDate, Orders orders) {
        Discounts discounts = new Discounts();
        for (DiscountPolicy policy : discountPolicies) {
            if (policy.isApplicable(visitDate, orders)) {
                int amount = policy.calculateDiscount(visitDate, orders);
                discounts.add(policy.getDiscountName(), amount);
            }
        }
        return discounts;
    }

    public Map<String, Integer> parseOrders(String input) {
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
