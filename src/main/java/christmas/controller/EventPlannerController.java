package christmas.controller;

import christmas.domain.benefit.Badge;
import christmas.domain.benefit.Giveaway;
import christmas.domain.date.VisitDate;
import christmas.domain.discount.DiscountCalculator;
import christmas.domain.discount.Discounts;
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
        outputView.printWelcome();

        VisitDate visitDate = readVisitDate();
        Orders orders = readOrders();

        outputView.printEventPreviewHeader(visitDate.getDate().getDayOfMonth());

        int originalPrice = orders.calculateTotalAmount();
        Discounts discounts = new DiscountCalculator(orders, visitDate).calculateDiscounts();
        Giveaway giveaway = Giveaway.from(orders);

        int totalBenefitAmount = discounts.getTotalAmount() + giveaway.getPrice();
        int finalPaymentAmount = originalPrice - discounts.getTotalAmount();
        Badge badge = Badge.from(totalBenefitAmount);

        outputView.printOrderMenu(orders);
        outputView.printTotalAmountBeforeDiscount(originalPrice);
        outputView.printGiveaway(giveaway);
        outputView.printBenefits(discounts, giveaway);
        outputView.printTotalBenefitAmount(totalBenefitAmount);
        outputView.printFinalAmount(finalPaymentAmount);
        outputView.printBadge(badge);
    }

    private VisitDate readVisitDate() {
        while (true) {
            try {
                String date = inputView.readDate();
                return VisitDate.from(date);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Orders readOrders() {
        while (true) {
            try {
                String input = inputView.readOrders();
                String refinedInput = input.replaceAll("\\s+", "");
                OrderParser parser = new OrderParser();
                return parser.readOrders(refinedInput);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
