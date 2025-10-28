package christmas.domain.benefit;

public enum Badge {
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000, "산타");

    Badge(int amount, String type) {
    }

    // TODO: amount에 따른 badge 종류 정해지는 기능 필요
}
