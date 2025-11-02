package christmas.controller.state;

import christmas.util.validator.DateValidator;

public class DateInputState implements State{

    @Override
    public void stateHandler(StateContext context) {
        while(true){
            try{
                int date = context.validateVisit();
                context.setDateSession(date);
                System.out.println("\n✓ 12월 " + date + "일 방문 예약이 확인되었습니다.");
                context.pop();
                context.push(new CategorySelectState());
                return;
            }catch (IllegalArgumentException e){
                System.err.printf(e.getMessage());
                System.out.println("입력 : ");
            }
        }
    }
}
