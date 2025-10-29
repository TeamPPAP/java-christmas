package christmas.service.discount;

import java.time.LocalDate;

public class ChristmasDiscount implements DiscountPolicy<LocalDate> {


    @Override
    public int calculateDiscount(LocalDate orderDate) {
        if (orderDate.isAfter(christmasDate)) {
            return 0;
        }

        int dayOfMonth = orderDate.getDayOfMonth();
        return (dayOfMonth - 1) * 100 + 1000;
    }
}
