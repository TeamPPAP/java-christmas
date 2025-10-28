package christmas;

import christmas.util.validator.IntegerValidator;
import christmas.util.validator.OrderValidator;
import christmas.util.validator.StringValidator;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        StringValidator stringValidator = new StringValidator();
        IntegerValidator integerValidator = new IntegerValidator();
        OrderValidator o = new OrderValidator(stringValidator,integerValidator);
        List<String> list = new ArrayList<>();
        list.add("타파스 - 16");
        list.add("제로콜라 - 6");
        o.totalCountWithinLimit(list);
    }
}
