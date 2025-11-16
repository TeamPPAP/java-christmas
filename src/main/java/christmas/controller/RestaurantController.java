package christmas.controller;

import static christmas.domain.model.Badge.awardBadge;

import christmas.domain.model.Badge;
import christmas.domain.model.Event;
import christmas.domain.model.Order;
import christmas.domain.model.OrderLine;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.util.input.InputFactory;
import christmas.util.input.InputReader;
import christmas.util.validator.DateValidator;
import christmas.util.validator.IntegerValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.*;

public class RestaurantController {
    private final InputFactory inputFactory = new InputFactory(new InputView());
    private final InputReader<Integer> integerReader = inputFactory.createIntegerReader();
    private final InputReader<String> stringReader = inputFactory.createStringReader();
    private final OutputView outputView = new OutputView();

    private final EventService eventService;
    private final OrderService orderService;
    private final IntegerValidator integerValidator;
    private final DateValidator dateValidator;

    public RestaurantController(EventService eventService, OrderService orderService,
                                IntegerValidator integerValidator) {
        this.eventService = eventService;
        this.orderService = orderService;
        this.integerValidator = integerValidator;
        this.dateValidator = new DateValidator(integerValidator);
    }

    public void run() {
        List<Order> orders = new ArrayList<>();
        //날짜 입력 & 검증
        int visit = greeting();
        //주문 메뉴 입력 & 검증
        orders = getOrders(orders);
        //서비스코드 없음
        outputView.printBenefitPreview();
        //주문 메뉴 출력
        outputView.printSelectedMenu(orders);
        //할인전 총 주문 금액
        outputView.printBeforeBenefitAffect(orderService.totalOrderPrice(orders));
        //증정품 증정 여부 확인
        outputView.printGift(eventService.isCalAmountForGift(orders));
        //혜택내역 종류별 출력
        outputView.printBenefitList(eventService.benefitVerifiedDate(orders, visit));
        //총 혜택 금액 출력
        int totalBenefitAmount = eventService.totalBenefitAmount(orders, visit);
        outputView.printTotalPrice(totalBenefitAmount);
        //할인 후 예상 결제금액 출력
        outputView.printExpectPurchaseAmount(eventService.calFinalAmount(orders,visit));
        //혜택 금액에 따른 배지 부여 출력
        Badge badge = awardBadge(totalBenefitAmount);;
        outputView.printBadgeAward(badge.getName());
    }

    private List<Order> getOrders(List<Order> orders) {
        while(true){
            try {
                outputView.printTakeOrder();
                List<String> order = orderService.splitOrderSentence(stringReader.read()); //주문 묶음 나누기
                // 메뉴/수량 나누기


                orders = orderService.confirmVerifiedOrder(order);
                break;
            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
        return orders;
    }

    private int greeting() {
        while (true) {
            try {
                outputView.printGreeting();
                return dateValidator.validateVisitDate(stringReader.read());
            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
    }

}
