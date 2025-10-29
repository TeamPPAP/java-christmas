package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {
    private LocalDate date;

    private final LocalDate christmas;

    public VisitDate(LocalDate date) {
        this.date = date;
        this.christmas = LocalDate.of(date.getYear(), 12, 25);
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
}
