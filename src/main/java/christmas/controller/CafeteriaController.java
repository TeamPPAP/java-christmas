package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderDetail;
import christmas.domain.VisitDate;
import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.service.CafeteriaService;
import christmas.service.benefit.dto.Giveaway;
import christmas.service.benefit.value.Badge;
import christmas.service.discount.dto.DiscountResult;

import java.util.List;

public class CafeteriaController {
    private final CafeteriaService cafeteriaService;
    private final InputView inputView;
    private final OutputView outputView;

    public CafeteriaController() {
        this.cafeteriaService = new CafeteriaService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        VisitDate date = inputView.readDate();
        List<OrderDetail> orderDetails = inputView.readMenu();
        Order order = new Order(orderDetails, date);

        outputView.printEventPreviewTitle(order.getVisitDate());
        outputView.printOrderItems(order);

        outputView.printTotalAmountBeforeDiscount(order.getTotalPrice());

        Giveaway giveawayMenu = cafeteriaService.getGiveawayMenu(order);
        outputView.printGiveaway(giveawayMenu);

        List<DiscountResult> totalDiscountList = cafeteriaService.getTotalDiscountList(order);
        outputView.printBenefitDetails(totalDiscountList);

        int totalBenefitAmount = totalDiscountList.stream()
            .mapToInt(DiscountResult::discountAmount)
            .sum();
        outputView.printTotalBenefitAmount(totalBenefitAmount);

        int finalPayment = order.getTotalPrice() - totalBenefitAmount;
        outputView.printFinalPayment(finalPayment);

        Badge eventBadge = cafeteriaService.getEventBadge(totalBenefitAmount);
        outputView.printEventBadge(eventBadge);
    }

}
