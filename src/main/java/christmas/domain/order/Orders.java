package christmas.domain.order;

import christmas.domain.menu.MenuType;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = new ArrayList<>(orders); // 불변 객체 유지를 위해 방어적 복사
        // TODO: 중복 메뉴 검증
        // TODO: 총 주문 개수 20개 이하 검증
        // TODO: 음료만 주문했는지 검증
    }

    public int calculateTotalAmount() {
        // TODO: 할인 전 총 주문 금액 계산
        return 0;
    }

    public int countMenuByType(MenuType menuType) {
        // TODO: 특정 타입 메뉴의 총 개수 계산 (할인 계산에 필요)
        return 0;
    }

    public List<Order> getOrders() {
        // TODO: 주문 목록 반환 (화면 출력용으로 필요할듯?)
        // 참조값 전달로 외부 수정 방지를 위해 새 리스트에 복사
        // 또는 unmodifiable 리스트 반환하거나 아예 스트링으로 변환해서 반환해도 좋을듯
        return new ArrayList<>(orders);
    }

    private void validateNoDuplicateMenus(List<Order> orders) {
        // TODO: 중복 메뉴 검증
    }

    private void validateTotalQuantity(List<Order> orders) {
        // TODO: 총 주문 개수 20개 이하 검증 - 필수 (요구사항)
    }

    private void validateNotOnlyBeverages(List<Order> orders) {
        // TODO: 음료만 주문했는지 검증 - 필수 (요구사항)
    }

}
