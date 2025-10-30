package christmas.service;

import static christmas.domain.model.defualtAmount.DefaultAmount.GIFT_AMOUNT;

import christmas.domain.model.Order;
import christmas.repository.MenuRepository;
import java.util.List;

public class OrderService {
    private MenuRepository menuRepo;
    private EventService eventService;

    public OrderService(MenuRepository menuRepo,EventService eventService) {
        this.menuRepo = menuRepo;
        this.eventService = eventService;
    }

    public int totalOrderPrice(List<Order> orders){
        int totalPrice = 0;
        for(int i=0;i<orders.size();i++){
            totalPrice += orders.get(i).getOrderPrice() * orders.get(i).getQuantity();
        }
        return totalPrice;
    }

    /**
     * 할인 후 예상 결제 금액
     * */
    public int calFinalAmount(List<Order> orders,int date){
        return totalOrderPrice(orders) - eventService.totalBenefitAmount(orders,date) + GIFT_AMOUNT.getAmount();
    }
}
