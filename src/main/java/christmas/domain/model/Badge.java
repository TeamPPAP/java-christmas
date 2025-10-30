package christmas.domain.model;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
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

    public String awardBadge (int benefitAmount){
        if (benefitAmount >= STAR.conditionAmount && benefitAmount <= TREE.conditionAmount) {
            return STAR.name;
        }
        if (benefitAmount >= TREE.conditionAmount && benefitAmount <= SANTA.conditionAmount) {
            return TREE.name;
        }
        if(benefitAmount >= SANTA.conditionAmount){
            return SANTA.name;
        }
        return NONE.name;
    }
}
