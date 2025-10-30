package christmas.domain.event;

import christmas.domain.entity.Order;
import christmas.domain.entity.VisitDate;

import java.time.LocalDate;

public class ChristmasEvent extends Discountable{
    final int INITIAL_DISCOUNT = 1000;
    final int DAILY_INCREASE_AMOUNT = 100;
    private int eventPeriod;

    protected ChristmasEvent() {
        super(EventType.DISCOUNT, LocalDate.of(2025, 12, 25));
    }

    @Override
    public void apply(VisitDate visitDate, Order order) {
        if(!super.isApplicable(visitDate, order))
            return ;

        // 1000원으로 체크해서 하루하루 늘어날떄마다 100원씩 추가(크리스마스 디데이만의 기능)
        setEventPeriod(visitDate);  // TODO : 생성자에서 반영해줘야하는 것이 아닌지.. 검토필요
        discount(order);

    }

    private void setEventPeriod(VisitDate visitDate){
        this.eventPeriod = visitDate.getDayOfMonth() -1;
    }

    @Override
    public void discount(Order order) {
        setDiscountPrice(INITIAL_DISCOUNT + (this.eventPeriod * DAILY_INCREASE_AMOUNT));
    }

    @Override
    public String toString() {
        return "크리스마스 디데이 할인: -" + super.discountPrice + "원";

    }
}
