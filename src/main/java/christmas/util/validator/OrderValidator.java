package christmas.util.validator;

import christmas.domain.model.Category;
import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import java.util.List;

public class OrderValidator {
    private static final int MAX_TOTAL_QUANTITY = 20;
    private final StringValidator stringValidator;
    private final IntegerValidator integerValidator;

    public OrderValidator(StringValidator stringValidator, IntegerValidator integerValidator) {
        this.integerValidator = integerValidator;
        this.stringValidator = stringValidator;
    }

    public void matchOrderPattern(List<String> list) {
        for (String string : list) {
            if (!string.matches("^\\s*(.+?)\\s*-\\s*([0-9]\\d*)\\s*$")) {
                throw new IllegalArgumentException("정해진 형식을 따라 입력해주세요.");
            }
        }
    }

    public void existMenuName(Order order, List<Menu> menuList) {
        Menu menuName = order.getOrderMenu();
        if (!menuList.contains(menuName)) {
            throw new IllegalArgumentException("존재하지 않는 메뉴입니다.");
        }
    }

    public void isOderListEmpty(List<Order> orderList) {
        if (orderList.isEmpty()) {
            throw new IllegalArgumentException("주문이 추가되지 않았습니다.");
        }
    }

    public void hasDuplicateMenu(Order order, List<Order> orderList){
        if(orderList.contains(order.getOrderMenu())){
            throw new IllegalArgumentException("이미 추가된 메뉴입니다.");
        }
    }

    public void onlyOrderDrink(List<Order> orderList) {
        for (Order order : orderList) {
            if (Category.DRINK != order.getOrderMenu().getCategory()) {
                return;
            }
        }
        throw new IllegalArgumentException("음료수만 주문할 수 없습니다.");
    }

    public void orderQuantityLeastOne(List<String> str){
        for(String s : str){
            String[] tmp = s.split("-");
            if(tmp[1].trim().equals("0")){
                throw new IllegalArgumentException("메뉴 개수는 0이 될 수 없습니다.");
            }
        }
    }

    public void totalCountWithinLimit(List<String> str){
        int sum = 0;
        for(String s : str){
            String[] tmp = s.split("-");
            sum += Integer.parseInt(tmp[1].trim());
        }
        if(sum> MAX_TOTAL_QUANTITY){
            throw new IllegalArgumentException("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        }
    }
}

//1000 + ((i-1)*100) = 최종 D-DAY 할인