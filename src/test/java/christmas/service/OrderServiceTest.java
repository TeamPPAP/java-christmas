package christmas.service;

import christmas.util.validator.IntegerValidator;
import christmas.util.validator.StringValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static christmas.domain.model.message.ErrorMessage.MENU_NOT_EXIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {
    private OrderService serv;

    @BeforeEach
    void setUp() {
        // 여기서 예외 안 나야 함
        serv = new OrderService(
                new StringValidator(),
                new IntegerValidator()
        );
    }

    @Test
    void 없는_메뉴가_있으면_예외_발생() {
        // given
        List<String> list = new ArrayList<>();
        list.add("바베큐립-1");   // 존재하는 메뉴
        list.add("없는메뉴-2");   // 메뉴판에 없는 메뉴

        // when & then
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> serv.confirmVerifiedOrder(list)  // 👈 예외는 반드시 이 람다 안에서만 터져야 함
        );

        // then (원하면)
        assertEquals(MENU_NOT_EXIST.getMessage(), e.getMessage());
    }
}