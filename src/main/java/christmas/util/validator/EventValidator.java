package christmas.util.validator;

import static christmas.domain.model.constant.FinalConstant.*;
import static christmas.domain.model.defualtAmount.DefaultAmount.BASE_DISCOUNT_AMOUNT;
import static christmas.domain.model.defualtAmount.DefaultAmount.GIFT_QUALIFYING_AMOUNT;

import christmas.domain.model.constant.Category;

import java.util.List;

public class EventValidator {
    private final DateValidator dateValidator;

    public EventValidator(DateValidator dateValidator) {
        this.dateValidator = dateValidator;
    }

    /**
     * 평일, 주말 할인 카테고리
     */
    public Category decideDiscountCategory(int date) {
        if (isWeekend(date)) {
            return Category.MAIN_DISH;
        }
        return Category.DESSERT;
    }

    /**
     * 특별행사 날짜 - 일요일, 25일
     */
    public boolean isSpecial(int date) {
        int tmp = date % 7;
        return tmp == 0 || date == XMAS.get();
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
        if (isSpecialDay(datesList, date)) {
            return BASE_DISCOUNT_AMOUNT.getAmount();
        }
        return 0;
    }

    /**
     * 별표시 날짜 확인
     *
     */
    public boolean isSpecialDay(List<Integer> datesList, int date) {
        return datesList.contains(date);
    }

    /**
     * 증정품 증정 여부 확인
     *
     */
    public boolean isGift(int amount) {
        return amount >= GIFT_QUALIFYING_AMOUNT.getAmount();
    }

    /**
     * 크리스마스 이전 날짜 확인
     *
     */
    public boolean isDateBeforeXmas(int date) {
        return date >= MIN_DAY.get() && date <= XMAS.get();
    }

    /**
     * 주말 판단 / boolean반환
     *
     */
    public boolean isWeekend(int date) {
        return date % 7 == 5 || date % 7 == 6;
    }

}
