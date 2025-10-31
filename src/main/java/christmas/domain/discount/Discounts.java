package christmas.domain.discount;

import java.util.Map;

public record Discounts(Map<String, Integer> discounts) {

    public Discounts(Map<String, Integer> discounts) {
        this.discounts = Map.copyOf(discounts);
    }

    public int getTotalAmount() {
        return discounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
