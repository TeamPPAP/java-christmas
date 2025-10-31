package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.entity.VisitDate;
import christmas.service.EventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    EventPlannerService service = new EventPlannerService();
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        // 1. 시작 메세지
        outputView.printWelcomeMessage();
        // 2. 방문 예정 날짜 입력 후 VisitDate 객체 반환
        VisitDate visitDate = applyVisitDate();
        // 3. 주문 받고 Order 객체 반환
        Order order = applyOrderDetails();
        // 4. 방문일자에 따른 예상 이벤트 혜택 확인
        outputView.printEventPreviewMessage(visitDate.getDayOfMonth());
        // 5. 주문 내역 확인
        System.out.println("<주문 메뉴>");
        System.out.println(order.getOrderSummary());
        // 6. 할인전 총 금액 확인
        outputView.printTotalBeforeDiscount(order.getTotalPrice());
        // 7. 이벤트별 혜택내역 확인
        outputView.printEvent(service.getEventSummeryByOrder(visitDate, order));
        // 8. 총혜택금액 확인
        // 9. 할인 후 예상 결제금액
        // 10. 12월 이벤트 배지
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
