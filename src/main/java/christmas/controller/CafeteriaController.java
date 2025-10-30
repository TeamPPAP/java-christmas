package christmas.controller;

import christmas.domain.Order;
import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.service.CafeteriaService;
import christmas.service.benefit.dto.Giveaway;
import christmas.service.benefit.value.Badge;
import christmas.service.discount.dto.DiscountResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        LocalDate localDate = inputView.readDate();
        List<String> menus = inputView.readMenu();
        Map<String, Integer> collect = cafeteriaService.getCollect(menus);

        Order order = new Order(collect, localDate);
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
