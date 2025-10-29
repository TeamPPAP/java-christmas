package christmas.domain.entity;

import christmas.domain.enums.MenuItem;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    Map<MenuItem, Integer> quantityByMenuItem;


    public Order(String menu) {
        parseMenuListWithStream(menu);
    };

    //TODO: 정적팩토리 메서드로 변환에 대해 검토
    public void parseMenuListWithStream(String menuLine) {
        // 1. 입력 문자열을 기반으로 메뉴와 수량 Map 생성
        Map<MenuItem, Integer> menuQuantityMap = Arrays.stream(menuLine.replace(" ", "").split(","))
                .filter(s -> !s.trim().isEmpty()) // 비어있는 문자열 제거
                .map(s -> s.split("-"))           // 각 항목을 "-" 기준으로 분리하여 String 배열로 변환
                .filter(arr -> arr.length == 2)   // 유효한 [메뉴, 수량] 쌍인지 확인
                .collect(Collectors.toMap(
                        arr -> MenuItem.of(arr[0]),   // 배열의 첫 번째 요소를 MenuItem 객체(Key)로 변환
                        arr -> Integer.parseInt(arr[1]) // 배열의 두 번째 요소를 Integer(Value)로 변환
                ));

        // 2. 결과 Map이 비어있는지 검증
        if (menuQuantityMap.isEmpty()) {
            throw new IllegalArgumentException("주문이 정상적으로 이루어지지 않았습니다.");
        }

        // 3. 클래스 필드에 최종 결과 할당
        this.quantityByMenuItem = menuQuantityMap;
    }


    public int getTotalPrice() {
        int totalPrice = 0;
        for (MenuItem menuItem : quantityByMenuItem.keySet()) {
            totalPrice += menuItem.getPrice() * quantityByMenuItem.get(menuItem);
        }
        return totalPrice;
    }

    public String getOrderSummary() {
        StringBuilder orderSummary = new StringBuilder();
        for (MenuItem menuItem : quantityByMenuItem.keySet()) {
            orderSummary.append(menuItem.getTitle())
                    .append(quantityByMenuItem.get(menuItem))
                    .append("개\n");
        }

        return orderSummary.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "quantityByMenuItem=" + quantityByMenuItem.toString() +
                '}';
    }
}



