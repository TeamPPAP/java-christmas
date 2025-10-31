package christmas.domain.date;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;

public class VisitDate implements Comparable<VisitDate> {

    private static final int YEAR = 2025;
    private static final int MONTH = 12;

    private static final EnumSet<DayOfWeek> WEEKDAYS =
            EnumSet.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);

    private static final EnumSet<DayOfWeek> WEEKENDS =
            EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    private final LocalDate date;

    private VisitDate(LocalDate date) {
        this.date = date;
    }

    public static VisitDate from(String dayOfMonth) {
        try {
            int day = Integer.parseInt(dayOfMonth);
            LocalDate date = LocalDate.of(YEAR, MONTH, day);
            return new VisitDate(date);
        } catch (NumberFormatException | DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public boolean isWeekday() {
        return WEEKDAYS.contains(date.getDayOfWeek());
    }

    public boolean isWeekend() {
        return WEEKENDS.contains(date.getDayOfWeek());
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public int compareTo(VisitDate o) {
        return this.date.compareTo(o.date);
    }
}
