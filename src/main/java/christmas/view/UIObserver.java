package christmas.view;

import christmas.controller.state.*;
import christmas.domain.model.Menu;
import christmas.domain.model.constant.Category;

import java.util.List;

public class UIObserver implements StateObserver{
    private final OutputView outputView;
    private State lastRenderedState = null;

    public UIObserver(OutputView outputView) {
        this.outputView = outputView;
    }
    @Override
    public void updateState(StateContext context) {
        State currentState = context.peek();

        if (currentState == null) {
            return;
        }

        // 새로운 상태로 전환될 때만 UI 렌더링
        if (currentState != lastRenderedState) {
            lastRenderedState = currentState;
            stateUI(context, currentState);
        }
    }

    private void stateUI(StateContext context, State state) {
        if (state instanceof DateInputState) {
            dateInputUI();
        }
        if (state instanceof CategorySelectState) {
            categorySelectUI(context);
        }
        if (state instanceof MenuSelectState menuSelectState) {
            menuSelectUI(context, menuSelectState);
        }
        if (state instanceof QuantityInputState) {
            quantityInputUI(context);
        }
    }

    private void dateInputUI() {
        outputView.printSeparator();
        outputView.printGreeting();
        outputView.printVisitDate();
        System.out.print("입력: ");
    }

    private void categorySelectUI(StateContext context) {
        outputView.printSeparator();
        outputView.printCurrentOrders(context.getOrders().getOrdersToList());
        outputView.printCategorySelect();

        System.out.println("[1] 에피타이저");
        System.out.println("[2] 메인디쉬");
        System.out.println("[3] 디저트");
        System.out.println("[4] 음료");
        System.out.println("[9] 주문완료 및 계산");
        System.out.println("[0] 뒤로 가기");
        System.out.print("선택: ");
    }

    private void menuSelectUI(StateContext context, MenuSelectState state) {
        Category category = state.getCategory();
        List<Menu> menus = context.getMenuList().stream()
                .filter(m -> m.getCategory() == category)
                .toList();

        outputView.printMenuSelect(category.getName());

        for (int i = 0; i < menus.size(); i++) {
            Menu m = menus.get(i);
            System.out.println("[" + (i + 1) + "] " + m.getMenuName() + " (" + m.getPrice() + "원)");
        }
        System.out.println("[0] 뒤로가기");
        System.out.print("선택: ");
    }

    private void quantityInputUI(StateContext context) {
        Menu selectedMenu = context.getSelectedMenu();
        if (selectedMenu != null) {
            outputView.printQuantity(selectedMenu.getMenuName());
            System.out.println("[0] 뒤로가기");
            System.out.print("수량: ");
        }
    }

}
