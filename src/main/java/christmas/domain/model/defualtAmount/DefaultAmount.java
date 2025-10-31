package christmas.domain.model.defualtAmount;

public enum DefaultAmount {
    WEEK_DISCOUNT_AMOUNT(2025),
    BASE_DISCOUNT_AMOUNT(1000),
    ADDITIONAL_DISCOUNT_AMOUNT_PER_DAY(100),
    GIFT_QUALIFYING_AMOUNT(120000),
    STANDARD_AMOUNT(10000),
    GIFT_AMOUNT(25000) ;
    final int amount;

    DefaultAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
