package christmas.service.impl;

import christmas.service.dto.Event;
import christmas.domain.model.Orders;
import christmas.service.EventPolicy;

import java.util.Optional;

import static christmas.domain.model.constant.FinalConstant.MIN_DAY;
import static christmas.domain.model.constant.FinalConstant.XMAS;
import static christmas.domain.model.defualtAmount.DefaultAmount.ADDITIONAL_DISCOUNT_AMOUNT_PER_DAY;
import static christmas.domain.model.defualtAmount.DefaultAmount.BASE_DISCOUNT_AMOUNT;

public class XmasDdayPolicy implements EventPolicy {
    @Override
    public Optional<Event> applyEvent(Orders orders, int date) {
        if (!orders.isSatisfyEventPolicy()) {
            return Optional.empty();
        }
        if(!isApplicableXmas(date)){
            return Optional.empty();
        }
        return Optional.of(createEvent(date));
    }

    private Event createEvent(int date) {
        return new Event("크리스마스 디데이 할인",-discountAmount(date));
    }

    private int discountAmount(int date) {
        return BASE_DISCOUNT_AMOUNT.getAmount() + (ADDITIONAL_DISCOUNT_AMOUNT_PER_DAY.getAmount() * (date-1));
    }
    private boolean isApplicableXmas(int date){
        return date >= MIN_DAY.get() && date <= XMAS.get();
    }
}
