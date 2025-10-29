package christmas.domain.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    private LocalDate date;

    public VisitDate(int dayOfMonth) {
        int firstDay = 1;
        int lastDay = 31;

        if(firstDay > dayOfMonth || dayOfMonth > lastDay) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }

        this.date = LocalDate.of(2025, 12, dayOfMonth);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY);
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }

    public boolean isBeforeChristmas() {
        return date.isBefore(LocalDate.of(2025, 12, 26));
    }

    public boolean specialDay() {
        return date.isEqual(LocalDate.of(2025, 12, 25))
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
