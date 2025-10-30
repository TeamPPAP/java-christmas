package christmas.domain.model;

public class Event {
    public final EventType eventName;
    public final int benefitPrice;

    public Event(EventType eventName, int benefitPrice) {
        this.eventName = eventName;
        this.benefitPrice = benefitPrice;
    }

    public EventType getEventName() {
        return eventName;
    }

    public int getBenefitPrice() {
        return benefitPrice;
    }
}
