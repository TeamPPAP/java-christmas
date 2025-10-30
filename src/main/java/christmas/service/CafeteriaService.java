package christmas.service;

import christmas.domain.Order;
import christmas.service.benefit.dto.Giveaway;
import christmas.service.benefit.value.Badge;
import christmas.service.discount.dto.DiscountResult;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class CafeteriaService {

    private final DiscountService discountService;
    private final BenefitService benefitService;

    public CafeteriaService() {
        this.discountService = new DiscountService();
        this.benefitService = new BenefitService();
    }

    public Map<String, Integer> getCollect(List<String> order) {
        return order.stream()
            .map(s -> s.split("-"))
            .collect(groupingBy(
                s -> s[0],
                summingInt(s -> Integer.parseInt(s[1]))
            ));
    }

    public List<DiscountResult> getTotalDiscountList(Order order) {
        return discountService.getDiscountDetails(new DiscountContext(order));
    }

    public Giveaway getGiveawayMenu(Order order) {
        return benefitService.GetGiveaway(order);
    }

    public Badge getEventBadge(int totalAmount) {
        return benefitService.getEventBadge(totalAmount);
    }
}
