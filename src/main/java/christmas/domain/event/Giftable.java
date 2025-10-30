package christmas.domain.event;

import christmas.domain.entity.Menu;
import christmas.domain.entity.Order;
import christmas.domain.enums.MenuItem;

import java.time.LocalDate;

public abstract class Giftable extends EventDetail{

    MenuItem gift;
    int giftCount = 0;

    protected Giftable(EventType eventType, LocalDate endDate) {
        super(eventType, endDate);
    }


    // 오더에 menu인자를 받아서 추가하는 메서드 추가
    abstract public void present(Order order);
}
