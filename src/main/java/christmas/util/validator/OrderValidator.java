package christmas.util.validator;
import static christmas.domain.model.message.ErrorMessage.*;
import static christmas.domain.model.constant.FinalConstant.*;

import christmas.domain.model.Category;
import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import java.util.List;

public class OrderValidator {
    private final StringValidator stringValidator;
    private final IntegerValidator integerValidator;
    public OrderValidator(StringValidator stringValidator, IntegerValidator integerValidator) {
        this.integerValidator = integerValidator;
        this.stringValidator = stringValidator;
    }
    public void matchOrderPattern(List<String> list) {
        for (String string : list) {
            if (!string.matches("^\\s*(.+?)\\s*-\\s*([0-9]\\d*)\\s*$")) {
                throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
            }
        }
    }
    public void existMenuName(Order order, List<Menu> menuList) {
        Menu menuName = order.getOrderMenu();
        if (!menuList.contains(menuName)) {
            throw new IllegalArgumentException(MENU_NOT_EXIST.getMessage());
        }
    }
    public void isOderListEmpty(List<Order> orderList) {
        if (orderList.isEmpty()) {
            throw new IllegalArgumentException(NO_ORDER_ADDED.getMessage());
        }
    }
    public void hasDuplicateMenu(Order order, List<Order> orderList){
        if(orderList.contains(order.getOrderMenu())){
            throw new IllegalArgumentException(DUPLICATE_MENU.getMessage());
        }
    }
    public void onlyOrderDrink(List<Order> orderList) {
        for (Order order : orderList) {
            if (Category.DRINK != order.getOrderMenu().getCategory()) {
                return;
            }
        }
        throw new IllegalArgumentException(DRINK_ONLY.getMessage());
    }
    public void orderQuantityLeastOne(List<String> str){
        for(String s : str){
            String[] tmp = s.split("-");
            if(tmp[1].trim().equals("0")){
                throw new IllegalArgumentException(ORDER_QTY_ZERO.getMessage());
            }
        }
    }
    public void totalCountWithinLimit(List<String> str){
        int sum = 0;
        for(String s : str){
            String[] tmp = s.split("-");
            sum += Integer.parseInt(tmp[1].trim());
        }
        if(sum > MAX_TOTAL_QUANTITY.get()){
            throw new IllegalArgumentException(ORDER_LIMIT_EXCEEDED.getMessage());
        }
    }
}
