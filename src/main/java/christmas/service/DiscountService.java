package christmas.service;

import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.dto.DiscountResult;
import christmas.service.discount.factory.DiscountFactory;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DiscountService {

    private final List<DiscountPolicy> policies = DiscountFactory.getInstance().getAllStrategies();

    public List<DiscountResult> getDiscountDetails(DiscountContext context) {
        if (!context.order().isEligibleForEvent()) {
            return Collections.emptyList();
        }

        return policies.stream()
            .map(policy -> policy.calculateDiscount(context))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

}
