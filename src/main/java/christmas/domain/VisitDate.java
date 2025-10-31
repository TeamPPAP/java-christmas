package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {
    private LocalDate date;

    private final LocalDate christmas;

    public VisitDate(String input) {
        if (isDateExceeded(input)) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        LocalDate date = LocalDate.parse("2025-12-" + String.format("%02d", Integer.parseInt(input)));
        this.date = date;
        this.christmas = LocalDate.of(this.date.getYear(), 12, 25);
    }

    public boolean isWeekend() {
        DayOfWeek value = date.getDayOfWeek();
        return value == DayOfWeek.SATURDAY || value == DayOfWeek.FRIDAY;
    }

    public boolean isSunday() {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isChristmas() {
        return date.equals(christmas);
    }

    public boolean isAfterChristmas() {
        return date.isAfter(christmas);
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    private boolean isDateExceeded(String day) {
        int date = Integer.parseInt(day);
        return date <= 0 || date > 31;
    }
}
