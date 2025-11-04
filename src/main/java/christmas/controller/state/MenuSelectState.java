package christmas.controller.state;

import christmas.domain.model.Menu;
import christmas.domain.model.constant.Category;

import java.util.List;

import static christmas.domain.model.message.ErrorMessage.*;

public class MenuSelectState implements State{
    private final Category category;

    public MenuSelectState(Category category) {
        this.category = category;
    }

    @Override
    public void stateHandler(StateContext context) {
        List<Menu> menus = context.getMenuList().stream().filter(menu -> menu.category() == category).toList();
        selectMenu(context, menus);
    }

    public void selectMenu(StateContext context, List<Menu> menus){
        while (true) {
            int sel;
            try {
                sel = context.parseJustInt();
            } catch (IllegalArgumentException e) {
                System.out.printf(INVALID_ORDER_FORMAT.getMessage());
                System.out.print("선택: ");
                continue;
            }

            if (isBack(sel)) {
                navigateBack(context);
                return;
            }

            if (isOutOfRange(sel, menus.size())) {
                System.out.printf(INVALID_SELECT_RANGE.getMessage(),1,menus.size());
                System.out.print("선택: ");
                continue;
            }

            goQuantityInput(context, menus, sel);
            return;
        }
    }

    private boolean isBack(int selection) {
        return selection == 0;
    }

    private boolean isOutOfRange(int selection, int range) {
        return selection < 1 || selection > range;
    }

    private void navigateBack(StateContext context) {
        context.pop();
        context.push(new CategorySelectState());
    }

    private void goQuantityInput(StateContext context, List<Menu> menus, int selection) {
        Menu chosen = menus.get(selection - 1);
        context.setSelectedMenu(chosen);
        context.pop();
        context.push(new QuantityInputState(category));
    }

}
