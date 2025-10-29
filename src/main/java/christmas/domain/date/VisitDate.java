package christmas.domain.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;

public record VisitDate(LocalDate date) implements Comparable<VisitDate> {

    private static final EnumSet<DayOfWeek> WEEKDAYS =
            EnumSet.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);

    private static final EnumSet<DayOfWeek> WEEKENDS =
            EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    public boolean isWeekday() {
        return WEEKDAYS.contains(date.getDayOfWeek());
    }

    public boolean isWeekend() {
        return WEEKENDS.contains(date.getDayOfWeek());
    }

    @Override
    public int compareTo(VisitDate o) {
        return this.date.compareTo(o.date);
    }

}
