package christmas.util.validator;

import static christmas.domain.model.message.ErrorMessage.*;

public  class IntegerValidator {

    /**
     * 빈문자열, 널 체킹 및 문자열 int형 전환
     * @param input 입력문자열
     * @return Int
     * **/
    public int parseNotBlankInt(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    public int quantityValidate(String input){
        int quantity = parseNotBlankInt(input);
        if(quantity<1){
            throw new IllegalArgumentException(ORDER_QTY_ZERO.getMessage());
        }
        return quantity;
    }
}
