package christmas.domain.model.constant;

public enum FinalConstant {
    MAX_DAY(31),
    MIN_DAY(1),
    XMAS(25),
    MAX_TOTAL_QUANTITY(20);

    private final int value;

    FinalConstant(int value) { this.value = value; }

    public int get() { return value; }
}
