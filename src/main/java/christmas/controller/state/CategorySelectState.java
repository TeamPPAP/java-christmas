package christmas.controller.state;

import christmas.domain.model.constant.Category;

import static christmas.domain.model.message.ErrorMessage.EMPTY_ORDER_LIST;
import static christmas.domain.model.message.ErrorMessage.SELECT_CATEGORY_NUMBER;

public class CategorySelectState implements State{
    @Override
    public void stateHandler(StateContext context) {
        while(true){
            int select;
            try{
                select = context.parseJustInt();
            }catch (IllegalArgumentException e){
                System.out.printf(e.getMessage());
                System.out.println("선택 : ");
                continue;
            }

            if(userSelect(context,select)){
                return;
            }
            System.out.println("선택 : ");

        }
    }

    private boolean userSelect(StateContext context, int select){
        if(select == 0){
            return goBackStart(context);
        }
        if(select == 9){
            return selectComplete(context);
        }
        Category selectedCategory = handleCategory(select);
        if(selectedCategory == null){
            System.out.println(SELECT_CATEGORY_NUMBER.getMessage());
            return false;
        }
        context.pop();
        context.push(new MenuSelectState(selectedCategory));
        return true;
    }

    private boolean goBackStart(StateContext context){
        System.out.print("\n처음으로 돌아가시겠습니까? 현재 주문 내역이 모두 삭제됩니다. (Y/N): ");
        try{
            String goback = context.readString();
            if(goback.equalsIgnoreCase("y")){
                System.out.println("처음 화면으로 돌아갑니다.");
                context.clearOrder();
                while(!context.isEmpty()){
                    context.pop();
                }
                context.push(new DateInputState());
                return true;
            }
        }catch(IllegalArgumentException e){
            System.out.printf(e.getMessage());
        }
        return false;
    }

    private boolean selectComplete(StateContext context){
        if(!context.hasOrders()){
            System.out.printf(EMPTY_ORDER_LIST.getMessage());
            return false;
        }
        context.pop();
        context.push(new CalculateState());
        return true;
    }

    private Category handleCategory(int select) {
        return switch (select) {
            case 1 -> Category.APPETIZER;
            case 2 -> Category.MAIN_DISH;
            case 3 -> Category.DESSERT;
            case 4 -> Category.DRINK;
            default -> null;
        };
    }
}
