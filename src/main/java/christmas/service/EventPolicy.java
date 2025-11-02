package christmas.service;

import christmas.service.dto.Event;
import christmas.domain.model.Orders;
import java.util.Optional;

public interface EventPolicy {
    Optional<Event> applyEvent(Orders orders, int date);
}