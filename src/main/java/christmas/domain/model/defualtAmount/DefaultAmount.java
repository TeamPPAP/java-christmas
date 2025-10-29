package christmas.domain.model.defualtAmount;

public enum DefaultAmount {
    WEEKDAY_DISCOUNT_AMOUNT(2025),
    DEFAULT_BASE_DISCOUNT_AMOUNT(1000),
    GIFT_QUALIFYING_AMOUNT(120000),
    STANDARD_AMOUNT(1000),
    GIFT_AMOUNT(25000);

    final int amount;

    DefaultAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
