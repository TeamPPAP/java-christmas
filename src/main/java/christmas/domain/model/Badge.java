package christmas.domain.model;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음",0);

    public final String name;
    public final int conditionAmount;

    Badge(String name, int conditionAmount) {
        this.name = name;
        this.conditionAmount = conditionAmount;
    }

    public String getName() {
        return name;
    }

    public int getConditionAmount() {
        return conditionAmount;
    }

    public static Badge awardBadge(int benefitAmount) {
        return Arrays.stream(values())
                .filter(badge -> benefitAmount >= badge.conditionAmount)
                .findFirst()
                .orElse(NONE);
    }
}
