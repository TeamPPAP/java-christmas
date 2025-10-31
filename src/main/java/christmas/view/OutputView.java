package christmas.view;

import christmas.domain.benefit.Badge;
import christmas.domain.benefit.Giveaway;
import christmas.domain.discount.Discounts;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class OutputView {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###");

    public void printWelcome() {
        System.out.println("안녕하세요! 포텐업 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenu() {
        Arrays.stream(MenuType.values())
                .filter(menuType -> menuType != MenuType.NONE)
                .forEach(this::printMenuSection);
    }

    private void printMenuSection(MenuType menuType) {
        System.out.println("\n<" + menuType.getName() + ">");
        String formattedMenus = Arrays.stream(Menu.values())
                .filter(menu -> menu.getType() == menuType)
                .filter(menu -> menu != Menu.NONE)
                .map(menu -> String.format("%s(%,d)", menu.getName(), menu.getPrice()))
                .collect(Collectors.joining(", "));
        System.out.println(formattedMenus);
    }

    public void printEventPreviewHeader(int day) {
        System.out.println("\n12월 " + day + "일에 포텐업 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrderMenu(Orders orders) {
        System.out.println("\n<주문 메뉴>");
        for (Order order : orders.getOrders()) {
            System.out.printf("%s %d개%n", order.getMenuName(), order.getQuantity());
        }
    }

    public void printTotalAmountBeforeDiscount(int originalPrice) {
        System.out.println("\n<할인 전 총 주문 금액>");
        System.out.printf("%s원%n", PRICE_FORMAT.format(originalPrice));
    }

    public void printGiveaway(Giveaway giveaway) {
        System.out.println("\n<증정 메뉴>");
        Menu giveawayMenu = giveaway.getMenu();
        if (giveawayMenu == Menu.NONE) {
            System.out.println("없음");
            return;
        }
        System.out.printf("%s %d개%n", giveawayMenu.getName(), giveaway.getQuantity());
    }

    public void printBenefits(Discounts discounts, Giveaway giveaway) {
        System.out.println("\n<혜택 내역>");
        boolean hasDiscounts = !discounts.discounts().isEmpty();
        boolean hasGiveaway = giveaway.getMenu() != Menu.NONE;

        if (!hasDiscounts && !hasGiveaway) {
            System.out.println(Menu.NONE.getName());
            return;
        }

        discounts.discounts().forEach(
                (name, amount) -> System.out.printf("%s: -%,d원%n", name, amount));

        if (hasGiveaway) {
            System.out.printf("%s: -%,d원%n", giveaway.getBenefitName(), giveaway.getPrice());
        }
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println("\n<총 혜택 금액>");
        if (totalBenefitAmount == 0) {
            System.out.println("0원");
            return;
        }
        System.out.printf("-%s원%n", PRICE_FORMAT.format(totalBenefitAmount));
    }

    public void printFinalAmount(int finalPaymentAmount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.printf("%s원%n", PRICE_FORMAT.format(finalPaymentAmount));
    }

    public void printBadge(Badge badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge.getType());
    }

    public void printErrorMessage(String message) {
        System.out.println(message + "\n");
    }

}
