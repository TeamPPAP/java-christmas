package christmas.util.validator;

import static org.junit.jupiter.api.Assertions.*;

import christmas.util.input.IntegerReader;
import christmas.view.InputView;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderValidatorTest {
    InputView inputView= new InputView();
    StringValidator stringValidator = new StringValidator();
    IntegerValidator integerValidator = new IntegerValidator();
    OrderValidator o = new OrderValidator(stringValidator,integerValidator);
    IntegerReader reader = new IntegerReader(inputView, integerValidator);
    @Test
    void matchOrderPattern() {
    }

    @Test
    void existMenuName() { // 없는 메뉴 테스트

    }

    @Test
    void isOderListEmpty() {
    }

    @Test
    void hasDuplicateMenu() {
    }

    @Test
    void onlyOrderDrink() {
    }

    @Test
    void orderQuantityLeastOne() {
        List<String> list = new ArrayList<>();
        list.add("타파스 - 20");
        list.add("제로콜라 - 0");
        o.orderQuantityLeastOne(list);
    }

    @Test
    void totalCountWithinLimit() {
        List<String> list = new ArrayList<>();
        list.add("타파스 - 20");
        list.add("제로콜라 - 0");
        o.totalCountWithinLimit(list);
        reader.read();
    }
}