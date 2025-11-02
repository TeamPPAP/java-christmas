package christmas.service.impl;

import christmas.service.dto.Event;
import christmas.domain.model.Orders;
import christmas.repository.EventPlanRepository;
import christmas.service.EventPolicy;

import java.util.List;
import java.util.Optional;

import static christmas.domain.model.defualtAmount.DefaultAmount.BASE_DISCOUNT_AMOUNT;

public class SpecialDayPolicy implements EventPolicy {
    EventPlanRepository eventPlanRepository;

    public SpecialDayPolicy(EventPlanRepository eventPlanRepository) {
        this.eventPlanRepository = eventPlanRepository;
    }

    @Override
    public Optional<Event> applyEvent(Orders orders, int date) {
        if(isSpecialDay(date) && orders.isSatisfyEventPolicy()){
            return Optional.of(createEvent());
        }
        return Optional.empty();
    }

    private int discountAmount() {
        return BASE_DISCOUNT_AMOUNT.getAmount();
    }


    private Event createEvent() {
        return new Event("특별 할인",discountAmount());
    }

    private boolean isSpecialDay(int date){
        List<Integer> datesList = eventPlanRepository.listSpecialDay();
        return datesList.contains(date);
    }
}
