package christmas.service;

import static christmas.domain.model.defualtAmount.DefaultAmount.BASE_DISCOUNT_AMOUNT;
import static christmas.domain.model.defualtAmount.DefaultAmount.GIFT_QUALIFYING_AMOUNT;
import static christmas.domain.model.defualtAmount.DefaultAmount.STANDARD_AMOUNT;
import static christmas.domain.model.defualtAmount.DefaultAmount.WEEKDAY_DISCOUNT_AMOUNT;

import christmas.domain.model.Category;
import christmas.domain.model.Order;
import christmas.repository.EventPlanRepository;
import christmas.util.validator.DateValidator;
import christmas.util.validator.EventValidator;
import christmas.util.validator.IntegerValidator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EventService {
    IntegerValidator integerValidator;
    DateValidator dateValidator;
    EventValidator eventValidator;
    EventPlanRepository eventPlanRepo;

    public EventService(IntegerValidator integerValidator, DateValidator dateValidator, EventValidator eventValidator,
                        EventPlanRepository eventPlanRepo) {
        this.integerValidator = integerValidator;
        this.dateValidator = dateValidator;
        this.eventValidator = eventValidator;
        this.eventPlanRepo = eventPlanRepo;
    }
    //테스트용 생성자
    public EventService() {
        this.integerValidator = new IntegerValidator();
        this.dateValidator = new DateValidator(integerValidator);
        this.eventValidator = new EventValidator(dateValidator);
        this.eventPlanRepo = new EventPlanRepository();
    }

    /**
     * 이벤트 적용 여부 판단 (총주문 금액 10,000원 이상)
     */
    public boolean isCalAmountForEvent(List<Order> orders) {
        return STANDARD_AMOUNT.getAmount() <= orders.stream().mapToInt(Order::getOrderPrice).sum();
    }

    /**
     * 크리스마스 디데이 할인 금액 계산 -(12/1~25, 1,000원부터 매일 100원 증가)
     */
    public int dDayDiscount(int date) {
        return BASE_DISCOUNT_AMOUNT.getAmount() + (eventValidator.dDayXmas(date) * 100);
    }

    /**
     * 평일,주말 할인 금액 계산
     */
    public int weekDiscount(List<Order> orders, int date) {
        AtomicInteger qty = new AtomicInteger(0);
        Category category = eventValidator.decideDiscountCategory(date);

        orders.forEach(order -> {
            if (category == order.getOrderMenu().getCategory()) {
                qty.set(qty.get() + order.getQuantity());
            }
        });
        return qty.get() * WEEKDAY_DISCOUNT_AMOUNT.getAmount();
    }

    /**
     * 특별 할인 금액 계산
     */
    public int specialDiscount(int date) {
        List<Integer> datesList = eventPlanRepo.listSpecialDay();
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
        return GIFT_QUALIFYING_AMOUNT.getAmount() <= total.get();
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
