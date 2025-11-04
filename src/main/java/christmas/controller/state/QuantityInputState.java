package christmas.controller.state;

import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import christmas.domain.model.constant.Category;

import static christmas.domain.model.message.ErrorMessage.DUPLICATE_MENU;
import static christmas.domain.model.message.ErrorMessage.ORDER_LIMIT_EXCEEDED;

public class QuantityInputState implements State{
    private final Category category;

    public QuantityInputState(Category category) {
        this.category = category;
    }

    @Override
    public void stateHandler(StateContext context) {
        Menu selectedMenu = context.getSelectedMenu();

        while(true){
            int quantity;
            try{
                quantity = context.quantityValidate();
            }catch (IllegalArgumentException e){
                System.out.printf(e.getMessage());
                System.out.println("수량 : ");
                continue;
            }
            if(quantity == 0){
                context.pop();
                if(category != null){
                    context.push(new MenuSelectState(category));
                }
                context.push(new CategorySelectState());
                return;
            }
            try {
                Order newOrder = new Order(selectedMenu, quantity);
                validateNewOrder(context, newOrder);
                context.addOrder(newOrder);
                System.out.println("✓ '" + selectedMenu.getMenuName() + " " + quantity + "개'가 주문 목록에 추가되었습니다.");
                context.pop();
                context.push(new CategorySelectState());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.print("수량: ");
            }
        }
    }
    private void validateNewOrder(StateContext context, Order newOrder) {
        checkDuplicateMenu(context, newOrder);
        checkTotalQuantityLimit(context, newOrder);
    }

    private void checkDuplicateMenu(StateContext context, Order newOrder) {
        String newMenuName = newOrder.getOrderMenu().getMenuName();
        boolean isDuplicate = context.getOrders().getOrdersToList().stream()
                .anyMatch(o -> o.getOrderMenu().getMenuName().equals(newMenuName));
        if (isDuplicate) {
            throw new IllegalArgumentException(String.format(DUPLICATE_MENU.getMessage(),newMenuName));
        }
    }

    private void checkTotalQuantityLimit(StateContext context, Order newOrder) {
        int currentTotal = context.getOrders().getOrdersToList().stream()
                .mapToInt(Order::getQuantity)
                .sum();
        int newTotal = currentTotal + newOrder.getQuantity();
        if (newTotal > 20) {
            throw new IllegalArgumentException(String.format(ORDER_LIMIT_EXCEEDED.getMessage(),currentTotal,newOrder.quantity()));
        }
    }
}
