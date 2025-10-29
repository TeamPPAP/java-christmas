package christmas.service.gift.value;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {

    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String label;
    private final int threshold;

    Badge(String label, int threshold) {
        this.label = label;
        this.threshold = threshold;
    }

    public String getLabel() {
        return label;
    }

    public int getThreshold() {
        return threshold;
    }

    public static Badge getBadge(int benefitAmount) {
        return Arrays.stream(values())
            .filter(b -> b.getThreshold() <= benefitAmount)
            .max(Comparator.comparing(Badge::getThreshold))
            .orElse(NONE);
    }
}
