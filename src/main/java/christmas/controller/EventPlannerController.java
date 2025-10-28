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
import christmas.domain.order.OrderParser;
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
        // TODO: 입력
        // TODO: 계산 ??
        // TODO: 출력
    }

    public Orders readOrders() {
        String input = inputView.readOrders();
        OrderParser parser = new OrderParser();
        return parser.readOrders(input);
    }

    // TODO: DiscountCalculator로 로직 분리?
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

}

// 전체적으로 컨트롤러가 너무 과한 책임을 갖고 있는 것 같아서 일단 오더 파싱하는 부분을 분리함
