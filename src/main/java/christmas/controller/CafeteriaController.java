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
        Order order = order();
        printOrders(order);

        List<DiscountResult> discountResults = discountOrders(order);
        int totalBenefitAmount = calculateAndPrintTotalDiscountAmount(discountResults);

        calculateFinalPayment(order, totalBenefitAmount);
        getBadge(totalBenefitAmount);
    }

    private Order order() {
        VisitDate date = inputView.readDate();
        List<OrderDetail> orderDetails = inputView.readMenu();
        return new Order(orderDetails, date);
    }

    private void printOrders(Order order) {
        outputView.printEventPreviewTitle(order.getVisitDate());
        outputView.printOrderItems(order);

        outputView.printTotalAmountBeforeDiscount(order.getTotalPrice());
    }

    private List<DiscountResult> discountOrders(Order order) {
        Giveaway giveawayMenu = cafeteriaService.getGiveawayMenu(order);
        outputView.printGiveaway(giveawayMenu);

        List<DiscountResult> totalDiscountList = cafeteriaService.getTotalDiscountList(order);
        outputView.printBenefitDetails(totalDiscountList);
        return totalDiscountList;
    }

    private int calculateAndPrintTotalDiscountAmount(List<DiscountResult> results) {
        int totalBenefitAmount = results.stream()
            .mapToInt(DiscountResult::discountAmount)
            .sum();
        outputView.printTotalBenefitAmount(totalBenefitAmount);
        return totalBenefitAmount;
    }

    private void calculateFinalPayment(Order order, int totalBenefitAmount) {
        int finalPayment = order.getTotalPrice() - totalBenefitAmount;
        outputView.printFinalPayment(finalPayment);
    }

    public void getBadge(int totalBenefitAmount) {
        Badge eventBadge = cafeteriaService.getEventBadge(totalBenefitAmount);
        outputView.printEventBadge(eventBadge);
    }

}
