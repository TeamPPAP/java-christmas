package christmas.domain.discount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Discounts {

    private final Map<String, Integer> discounts;

    public Discounts() {
        this.discounts = new HashMap<>();
    }

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

    public Map<String, Integer> getDiscounts() {
        return Collections.unmodifiableMap(discounts);
    }

}
