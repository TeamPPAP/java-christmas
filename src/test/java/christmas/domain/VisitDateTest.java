package christmas.domain;

import christmas.domain.entity.VisitDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class VisitDateTest {

    @Test
    void isWeekend() {
        //given
        int dayOfWeek = 6;

        //when
        VisitDate visitDate = new VisitDate(dayOfWeek);

        //then
        Assertions.assertTrue(visitDate.isWeekend(), "visitDate should be weekend");
    }

    @Test
    void isWeekDay() {

    }

    @Test
    void isDuringPeriod() {
        int date = 1;
        VisitDate visitDate = new VisitDate(date);

        boolean result = visitDate.isDuringPeriod(LocalDate.of(2025, 12, 1)
                , LocalDate.of(2025, 12, 31));

        Assertions.assertTrue(result, visitDate.toString() + "은 12월 내에 있어야합니다.");
    }

    @Test
    void isBeforeChristmas() {
        //given
        int date = 26;
        VisitDate visitDate = new VisitDate(date);
        //when
        boolean result = visitDate.isBeforeChristmas();
        //then
        Assertions.assertTrue(result, "visitDate should be before christmas");
    }

    @Test
    void specialDay() {
        //given
        int date = 25;
        VisitDate visitDate = new VisitDate(date);
        //when
        boolean result = visitDate.isSpecialDay();
        //then
        Assertions.assertTrue(result, "specialDay should be true");
    }
}