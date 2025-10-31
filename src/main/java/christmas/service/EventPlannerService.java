package christmas.service;

import christmas.domain.order.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.event.Benefit;
import christmas.domain.event.discount.ChristmasEvent;
import christmas.domain.event.discount.SpecialEvent;
import christmas.domain.event.discount.WeekDayEvent;
import christmas.domain.event.discount.WeekendEvent;
import christmas.domain.event.gift.GiftEvent;

import java.util.List;
import java.util.stream.Collectors;

public class EventPlannerService {
    final List<Benefit> EVENTLIST = List.of(
            new ChristmasEvent(), new SpecialEvent(), new WeekDayEvent(),
            new WeekendEvent(), new GiftEvent());

    public String getEventSummeryByOrder(VisitDate visitDate, Order order){
        return EVENTLIST.stream().map(event -> {
                    event.apply(visitDate, order);
                    return event.toString();
                }).collect(Collectors.joining("\n\n"));
    }

}
