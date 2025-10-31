package christmas.service;

import christmas.domain.Order;
import christmas.service.benefit.dto.Giveaway;
import christmas.service.benefit.value.Badge;
import christmas.service.discount.dto.DiscountResult;

import java.util.List;

public class CafeteriaService {

    private final DiscountService discountService;
    private final BenefitService benefitService;

    public CafeteriaService() {
        this.discountService = new DiscountService();
        this.benefitService = new BenefitService();
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
