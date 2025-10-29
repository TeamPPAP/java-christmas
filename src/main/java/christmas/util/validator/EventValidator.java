package christmas.util.validator;

import static christmas.domain.model.defualtAmount.DefaultAmount.BASE_DISCOUNT_AMOUNT;

import christmas.domain.model.Category;
import java.util.List;

public class EventValidator {
    DateValidator dateValidator;

    public EventValidator(DateValidator dateValidator) {
        this.dateValidator = dateValidator;
    }

    /**
     * 평일, 주말 할인 카테고리
     */
    public Category decideDiscountCategory(int date) {
        if(dateValidator.isWeekend(date)){
            return Category.MAIN_DISH;
        }
        return Category.DESSERT;
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
            return BASE_DISCOUNT_AMOUNT.getAmount();
        }
        return 0;
    }
}
