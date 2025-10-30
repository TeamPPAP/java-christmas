package christmas.domain.event;

import christmas.domain.event.EventDetail;

public enum Event {
    CHRISTMAS_D_DAY(new ChristmasEvent()),
    WEEKDAY(new WeekDayEvent()),
    WEEKEND(new WeekendEvent()),
    SPECIAL(new SpecialEvent()),
    GIFT(new GiftEvent());

    final EventDetail eventDetail;

    Event(EventDetail eventDetail) {
        this.eventDetail = eventDetail;
    }

    public EventDetail getEventDetail() {
        return eventDetail;
    }
}
