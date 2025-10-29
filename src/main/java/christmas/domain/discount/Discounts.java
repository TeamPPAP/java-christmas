package christmas.domain.discount;

import java.util.Map;

public record Discounts(Map<String, Integer> discounts) {

    public Discounts(Map<String, Integer> discounts) {
        this.discounts = Map.copyOf(discounts);
    }

    @Deprecated
    public void add(String discountName, int amount) {
        discounts.put(discountName, amount);
    }

    public int getTotalAmount() {
        return discounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isEmpty() {
        return discounts.isEmpty();
    }

}
