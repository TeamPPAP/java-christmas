package christmas.service.impl;

import christmas.domain.model.Menu;
import christmas.domain.model.Orders;
import christmas.service.GiftPolicy;
import christmas.service.MenuService;
import christmas.service.dto.Gift;

import java.util.List;
import java.util.Optional;

import static christmas.domain.model.defualtAmount.DefaultAmount.GIFT_QUALIFYING_AMOUNT;

public class GiftEventPolicy implements GiftPolicy {
    MenuService menuService;

    public GiftEventPolicy(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public Optional<Gift> isSatisfy(Orders orders, int date) {
        if(isApplicableGift(orders)){
            return giftMenu();
        }
        return Optional.empty();
    }

    private Optional<Gift> giftMenu() {
        List<Menu> menus = menuService.getAllMenu();
        return menus.stream()
                .filter(menu -> menu.menuName().equals("샴페인"))
                .findFirst()
                .map(menu -> new Gift(menu, 1));
    }

    private boolean isApplicableGift(Orders orders){
        return orders.totalOrderAmount() >= GIFT_QUALIFYING_AMOUNT.getAmount();
    }
}
