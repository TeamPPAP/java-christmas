package christmas.service;

import christmas.domain.model.Category;
import christmas.domain.model.Order;
import christmas.repository.SpecialDayRepository;
import christmas.util.validator.DateValidator;
import christmas.util.validator.EventValidator;
import christmas.util.validator.IntegerValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EventService {
    IntegerValidator integerValidator = new IntegerValidator();
    DateValidator dateValidator = new DateValidator(integerValidator);
    EventValidator eventValidator = new EventValidator();
    SpecialDayRepository specialDayRepository =  new SpecialDayRepository();

    private final int WEEKDAY_DISCOUNT_AMOUNT = 2025;
    private final int DEFAULT_BASE_DISCOUNT_AMOUNT = 1000;
    private final int GIFT_QUALIFYING_AMOUNT = 120000;
    private final int STANDARD_AMOUNT = 10000;
    private final int GIFT_AMOUNT = 25000;

    /**
     * 이벤트 적용 여부 판단 (총주문 금액 10,000원 이상)
     */
    public boolean isCalAmountForEvent(List<Order> orders) {
        return STANDARD_AMOUNT <= orders.stream().mapToInt(Order::getOrderPrice).sum();
    }

    /**
     * 크리스마스 디데이 할인 금액 계산
     -(12/1~25, 1,000원부터 매일 100원 증가)
     */
    public int dDayDiscount(int date) {
        return DEFAULT_BASE_DISCOUNT_AMOUNT + (eventValidator.dDayXmas(date) * 100);
    }

    /**
     * 평일,주말 할인 금액 계산
     */
    public int weekDiscount(List<Order> orders, Category category) {
        AtomicInteger qty = new AtomicInteger(0);
        orders.forEach(order -> {
            if (category == order.getOrderMenu().getCategory()) {
                qty.set(qty.get() + order.getQuantity());
            }
        });
        return qty.get() * WEEKDAY_DISCOUNT_AMOUNT;
    }

    /**
     * 특별 할인 금액 계산
     */
    public int specialDiscount(int date) {
        List<Integer> datesList = specialDayRepository.listSpecialDay();
        return eventValidator.getDefaultBaseDiscountAmount(datesList, date);
    }

    /**
     * 증정을 위한 총 주문 금액 계산
     */
    public boolean isCalAmountForGift(List<Order> orders) {
        AtomicInteger total = new AtomicInteger(0);
        orders.forEach(order -> {
            total.set(total.get() + (order.getQuantity() * order.getOrderMenu().getPrice()));
        });
        return GIFT_QUALIFYING_AMOUNT <= total.get();
    }

    /**
     * 총 혜택 금액 계산 (모든 할인 금액 합계 + 증정 메뉴 가격
     */
    /*public int totalBenefitAmount(List<Order> orders, int date) {
        isCalAmountForEvent(orders);
        dDayDiscount(date);
        weekDiscount(orders, date)
        specialDiscount()
        isCalAmountForGift()
    }
*/
}
