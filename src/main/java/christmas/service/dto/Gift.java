package christmas.service.dto;

import christmas.domain.model.Menu;

public record Gift(
        Menu giftMenu,
        int quantity
) {}
