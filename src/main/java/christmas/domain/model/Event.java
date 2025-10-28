package christmas.domain.model;

public class Event {
    EventType eventName;
    int benefitPrice;

    public EventType getEventName() {
        return eventName;
    }

    public void setEventName(EventType eventName) {
        this.eventName = eventName;
    }

    public int getBenefitPrice() {
        return benefitPrice;
    }

    public void setBenefitPrice(int benefitPrice) {
        this.benefitPrice = benefitPrice;
    }
}
