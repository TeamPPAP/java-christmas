package christmas.controller;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.ChristmasDDayDiscount;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.Discounts;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.domain.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

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
        // 입력
        // 계산
        // 출력
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
}
