package christmas.domain.model;

public class Event {
    EventType eventName;
    int benefitPrice;

    public Event(EventType eventName, int benefitPrice) {
        this.eventName = eventName;
        this.benefitPrice = benefitPrice;
    }

    public EventType getEventName() {
        return eventName;
    }

    public void setEventName(EventType eventName) {
        this.eventName = eventName;
    }

    public int getBenefitPrice() {
        return benefitPrice;
    }

}
