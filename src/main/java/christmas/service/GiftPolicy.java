package christmas.service;

import christmas.domain.model.Orders;
import christmas.service.dto.Gift;

import java.util.Optional;

public interface GiftPolicy {
    Optional<Gift> isSatisfy(Orders orders, int date);
}
