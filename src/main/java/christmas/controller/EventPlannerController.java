package christmas.controller;

import christmas.domain.entity.Order;
import christmas.domain.entity.VisitDate;
import christmas.service.EventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    EventPlannerService service = new EventPlannerService();

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        // 1. 시작 메세지 출력
        outputView.printWelcomeMessage();
        // 2. 방문 예정 날짜 입력 후 VisitDate 객체 반환
        VisitDate visitDate = applyVisitDate();
        // 3. 주문 받고 Order 객체 반환
        Order order = applyOrderDetails();

        outputView.printEventPreviewMessage(visitDate.getDayOfMonth());

        System.out.println(order.getOrderSummary());

        outputView.printTotalBeforeDiscount(order.getTotalPrice());


    }

    public VisitDate applyVisitDate() {
        outputView.askVisitDate();
        return new VisitDate(inputView.getVisitDate());
    }

    public Order applyOrderDetails() {
        outputView.askForOrderDetails();
        return new Order(inputView.getOrderDetails());
    }


}
