package christmas.controller;

import christmas.domain.order.OrderParser;
import christmas.domain.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventPlannerController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();

    }

    public void run() {
        // TODO: 입력
        // TODO: 계산 ??
        // TODO: 출력
    }

    public Orders readOrders() {
        String input = inputView.readOrders();
        OrderParser parser = new OrderParser();
        return parser.readOrders(input);
    }

}
