package christmas.service;

import christmas.domain.model.Orders;
import christmas.service.dto.BenefitResult;
import christmas.service.dto.Event;
import christmas.service.dto.Gift;

import java.util.List;
import java.util.Optional;

public class BenefitCalculator {
    private final List<EventPolicy> eventPolicies;
    private final GiftPolicy giftPolicy;

    public BenefitCalculator(List<EventPolicy> eventPolicies, GiftPolicy giftPolicy) {
        this.eventPolicies = eventPolicies;
        this.giftPolicy = giftPolicy;
    }

    public BenefitResult calculateBenefit(Orders orders, int date) {
        List<Event> applicableEvents = verifiedEvents(orders, date);
        Optional<Gift> gift = giftPolicy.isSatisfy(orders, date);

        int totalDiscountAmount = calTotalDiscountAmount(applicableEvents);
        int giftBenefitAmount = calGiftBenefitAmount(gift);
        int totalBenefitAmount = calTotalBenefitAmount(totalDiscountAmount, giftBenefitAmount);
        int finalPurchaseAmount = calFinalPurchaseAmount(orders, totalDiscountAmount);

        return new BenefitResult(applicableEvents, totalDiscountAmount, gift, totalBenefitAmount, finalPurchaseAmount);
    }

    private List<Event> verifiedEvents(Orders orders, int date) {
        return eventPolicies.stream()
                .map(policy -> policy.applyEvent(orders, date))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private int calGiftBenefitAmount(Optional<Gift> gift) {
        return gift.map(g -> g.giftMenu().getPrice() * g.quantity())
                .orElse(0);
    }

    private int calTotalDiscountAmount(List<Event> events) {
        return events.stream()
                .mapToInt(Event::discountAmount)
                .sum();
    }

    private int calTotalBenefitAmount(int totalDiscountAmount, int giftBenefitAmount) {
        return totalDiscountAmount + giftBenefitAmount;
    }

    private int calFinalPurchaseAmount(Orders orders, int totalDiscountAmount) {
        int totalAmountBeforeBenefit = orders.totalOrderAmount();
        return totalAmountBeforeBenefit - totalDiscountAmount;
    }
}
