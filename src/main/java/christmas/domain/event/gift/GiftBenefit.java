package christmas.domain.event.gift;

import christmas.domain.order.Order;
import christmas.domain.menu.MenuItem;
import christmas.domain.event.Benefit;

import java.util.Objects;

public interface GiftBenefit extends Benefit {

    void present(Order order);

    default String getBenefitSummary(String title, MenuItem gift, int giftCount){
        if( giftCount <= 0 || Objects.isNull(gift))
            return title.concat(": -");

        return String.format("%s: %s %d개", title, gift.getTitle(), giftCount );
    }
}
