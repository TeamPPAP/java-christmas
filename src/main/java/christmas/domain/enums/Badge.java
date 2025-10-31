package christmas.domain.enums;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);

    private final String title;
    private final int minimumOrderAmount;

    Badge(String title, int minimumOrderAmount) {
        this.title = title;
        this.minimumOrderAmount = minimumOrderAmount;
    }
}
