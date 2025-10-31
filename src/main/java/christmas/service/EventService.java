package christmas.service;

import static christmas.domain.model.defualtAmount.DefaultAmount.BASE_DISCOUNT_AMOUNT;
import static christmas.domain.model.defualtAmount.DefaultAmount.GIFT_AMOUNT;
import static christmas.domain.model.defualtAmount.DefaultAmount.GIFT_QUALIFYING_AMOUNT;
import static christmas.domain.model.defualtAmount.DefaultAmount.STANDARD_AMOUNT;
import static christmas.domain.model.defualtAmount.DefaultAmount.WEEK_DISCOUNT_AMOUNT;
import static christmas.domain.model.EventType.*;

import christmas.domain.model.Category;
import christmas.domain.model.Event;
import christmas.domain.model.EventType;
import christmas.domain.model.Order;
import christmas.domain.model.defualtAmount.DefaultAmount;
import christmas.repository.EventPlanRepository;
import christmas.util.validator.EventValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class EventService {
    private final EventValidator eventValidator;
    private final EventPlanRepository eventPlanRepo;
    private final OrderService orderService;

    public EventService(EventValidator eventValidator,
                        EventPlanRepository eventPlanRepo, OrderService orderService) {
        this.eventValidator = eventValidator;
        this.eventPlanRepo = eventPlanRepo;
        this.orderService = orderService;
    }

    public List<Integer> getSpecialDayList() {
        return eventPlanRepo.listSpecialDay();
    }

    /**
     * 이벤트 적용 여부 판단 (총주문 금액 10,000원 이상)
     */
    public boolean isCalAmountForEvent(List<Order> orders) {
        return STANDARD_AMOUNT.getAmount() <= orderService.totalOrderPrice(orders);
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
        return qty.get() * WEEK_DISCOUNT_AMOUNT.getAmount();
    }

    /**
     * 특별 할인 금액 계산
     */
    public int specialDiscount(int date) {
        List<Integer> datesList = eventPlanRepo.listSpecialDay();
        return eventValidator.getDefaultBaseDiscountAmount(datesList, date);
    }

    /**
     * 증정을 위한 총 주문 금액 계산 - 샴페인 증정 대상 확인
     */
    public boolean isCalAmountForGift(List<Order> orders) {
        return GIFT_QUALIFYING_AMOUNT.getAmount() <= orderService.totalOrderPrice(orders);
    }


    /**
     * 총 혜택 금액 계산 (모든 할인 금액 합계 + 증정 메뉴 가격
     */
    public int totalBenefitAmount(List<Order> orders, int date) {
        return benefitVerifiedDate(orders,date).stream().mapToInt(Event::getBenefitPrice).sum();
    }

    private int verifiedGiftAmount(List<Order> orders) {
        if (isCalAmountForGift(orders)) {
            return GIFT_AMOUNT.getAmount();
        }
        return 0;
    }

    /**
     * Event객체에 값 주입
     *
     */
    public List<Event> benefitVerifiedDate(List<Order> orders, int date) {
        List<Event> events = new ArrayList<>();
        List<Integer> datesList = getSpecialDayList();

        if (isCalAmountForEvent(orders)) {
            addWeekEventToList(orders, date).stream()
                    .filter(event -> event.benefitPrice != 0)
                    .forEach(events::add);
            addBeforeXmasDDayToList(date).ifPresent(events::add);
            addSpecialDayToList(date, datesList).ifPresent(events::add);
            addGiftToEventList(orders).ifPresent(events::add);
        }
        return events;
    }

    /**
     * 평일,주말 할인 여부 판단 및 적용 시 할인 가격 Event 객체 생성
     *
     */
    private Optional<Event> addWeekEventToList(List<Order> orders, int date) {
        EventType eventType = isWeekend(date);
        return Optional.of(new Event(eventType, weekDiscount(orders, date)));
    }

    /**
     * 크리스마스 디데이 할인 적용여부 판단 및 적용 시 할인 가격 Event 객체 생성
     *
     */
    private Optional<Event> addBeforeXmasDDayToList(int date) {
        if (eventValidator.isDateBeforeXmas(date)) {
            return Optional.of(new Event(XMAS_DISCOUNT, dDayDiscount(date)));
        }
        return Optional.empty();
    }

    /**
     * 별표시 이벤트 적용여부 판단 및 적용 시 할인 가격 Event 객체 생성
     *
     */
    private Optional<Event> addSpecialDayToList(int date, List<Integer> datesList) {
        if (eventValidator.isSpecialDay(datesList, date)) {
            return Optional.of(new Event(SPECIAL_DISCOUNT, specialDiscount(date)));
        }
        return Optional.empty();
    }

    /**
     * 증정품 조건 만족시 이벤트 리스트에 증정이벤트 추가
     *
     */
    private Optional<Event> addGiftToEventList(List<Order> orders) {
        if (eventValidator.isGift(orderService.totalOrderPrice(orders))) {
            return Optional.of(new Event(EventType.GIVE_GIFT, DefaultAmount.GIFT_AMOUNT.getAmount()));
        }
        return Optional.empty();
    }

    /**
     * 주말 판단/EventType반환
     *
     */
    private EventType isWeekend(int date) {
        if (eventValidator.isWeekend(date)) {
            return WEEKENDS_DISCOUNT;
        }
        return WEEKDAYS_DISCOUNT;
    }
    /**
     * 할인 후 예상 결제 금액
     * */
    public int calFinalAmount(List<Order> orders,int date){
        return (orderService.totalOrderPrice(orders) - totalBenefitAmount(orders,date)) + verifiedGiftAmount(orders);
    }
}
