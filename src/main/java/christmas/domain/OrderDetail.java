package christmas.domain;

import christmas.domain.value.Menu;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

public class OrderDetail {
    private Menu menu;
    private int cnt;

    public OrderDetail(String menu, int cnt) {
        if (cnt <= 0) {
            throw new IllegalArgumentException("cnt must be greater than 0");
        }

        this.menu = Menu.getByName(menu);
        this.cnt = cnt;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCnt() {
        return cnt;
    }

    public int getTotalPrice() {
        return menu.getPrice() * cnt;
    }

    @Override
    public String toString() {
        return menu.getMenuName() + " " + cnt + "개";
    }

    public static List<OrderDetail> createOrderDetail(List<String> order) {
        List<OrderDetail> orderDetails = create(getCollect(order));
        if (orderDetails.isEmpty() || orderDetails.size() > 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return orderDetails;
    }

    private static Map<String, Integer> getCollect(List<String> order) {
        return order.stream()
            .map(s -> s.split("-"))
            .collect(groupingBy(
                s -> s[0],
                summingInt(s -> Integer.parseInt(s[1]))
            ));
    }


    private static List<OrderDetail> create(Map<String, Integer> order) {
        return order.entrySet().stream()
            .map(entry -> new OrderDetail(entry.getKey(), entry.getValue()))
            .collect(toList());
    }

}
