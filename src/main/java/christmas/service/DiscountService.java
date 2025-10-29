package christmas.service;

import christmas.domain.Order;
import christmas.service.discount.dto.DiscountResult;
import christmas.service.discount.factory.DiscountFactory;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiscountService {

    public int calculateTotalDiscount(Order order) {
        return getDiscountResultStream(order)
            .mapToInt(DiscountResult::discountAmount)
            .sum();
    }

    public String getBenefitDetails(Order order) {
        return getDiscountResultStream(order).map(DiscountResult::toString)
            .collect(Collectors.joining("\n"));
    }

    public Stream<DiscountResult> getDiscountResultStream(Order order) {
        return DiscountFactory.getInstance().getAllStrategies().stream()
            .map(discountPolicy -> discountPolicy.calculateDiscount(order))
            .filter(Objects::nonNull);
    }

}
