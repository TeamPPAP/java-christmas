package christmas.util.validator;

import static christmas.domain.model.defualtAmount.DefaultAmount.DEFAULT_BASE_DISCOUNT_AMOUNT;

import java.util.List;

public class EventValidator {
    /**
     * 주말이면 true 반환
     */
    public boolean isWeekend(int date) {
        int tmp = date % 7;
        return tmp == 5 || tmp == 6;
    }

    /**
     * 특별행사 날짜
     * - 일요일, 25일
     */
    public boolean isSpecial(int date) {
        int tmp = date % 7;
        return tmp == 0 || date == 25;
    }

    /**
     * 날짜가 크리스마스와 얼마나 가까운지
     */
    public int dDayXmas(int date) {
        return date - 1;
    }

    /**
     * 기본 할인 금액 반환
     */
    public int getDefaultBaseDiscountAmount(List<Integer> datesList, int date) {
        if (datesList.contains(date)) {
            return DEFAULT_BASE_DISCOUNT_AMOUNT.getAmount();
        }
        return 0;
    }
}
