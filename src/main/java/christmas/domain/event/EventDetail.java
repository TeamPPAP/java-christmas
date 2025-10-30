package christmas.domain.event;

import christmas.domain.entity.Order;
import christmas.domain.entity.VisitDate;
import christmas.domain.enums.MenuCategory;

import java.time.LocalDate;

public abstract class EventDetail {
    final LocalDate eventStartDate;
    final LocalDate eventEndDate;
    final EventType eventType;

    protected EventDetail(EventType eventType, LocalDate endDate) {
        this.eventStartDate = LocalDate.of(2025, 12, 1);
        this.eventEndDate = endDate;
        this.eventType = eventType;
    }

    abstract public void apply(VisitDate visitDate, Order order);

    boolean isApplicable(VisitDate visitDate, Order order){
        // 1. 기간체크(공통)
        if(!visitDate.isDuringPeriod(eventStartDate, eventEndDate)) {
            return false;
        }

        // 2. 총주문금액 10000원 이상인지 체크(공통)
        if(order.getTotalPrice() < 10000) {
            return false;
        }

        // 3. 음료만 주문할시 주문할 수 없음(음료만 있는지 체크하는 서비스 위치 Order)(공통)
        if(order.containsOnlyCategory(MenuCategory.DRINK)) {
            return false;
        }

        return true;

    }


}
