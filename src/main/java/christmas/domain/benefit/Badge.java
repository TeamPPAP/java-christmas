package christmas.domain.benefit;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
    NONE(0, "없음"),
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000, "산타");

    private final int amount;
    private final String type;

    Badge(int amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public static Badge from(int amount) {
        return Arrays.stream(Badge.values())
                .sorted(Comparator.comparingInt(Badge::getAmount).reversed())
                .filter(badge -> badge.getAmount() <= amount)
                .findFirst()
                .orElse(NONE);
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

}
