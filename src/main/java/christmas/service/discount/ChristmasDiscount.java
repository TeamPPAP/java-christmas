package christmas.service.discount;

import java.time.LocalDate;

public class ChristmasDiscount implements DiscountPolicy {
    private final LocalDate christmasDate = LocalDate.of(LocalDate.now().getYear(), 12, 25);
    private LocalDate orderDate;

    public ChristmasDiscount(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int calculateDiscount() {
        if (orderDate.isAfter(christmasDate)) {
            return 0;
        }

        int dayOfMonth = orderDate.getDayOfMonth();
        return (dayOfMonth - 1) * 100 + 1000;
    }
}
