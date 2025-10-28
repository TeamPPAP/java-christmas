package christmas.domain.date;

import java.time.LocalDate;

/**
 * 사용자의 예약을 저장하는 클래스
 */
public record VisitDate(LocalDate date) implements Comparable<VisitDate> {

    public boolean isWeekday() {
        return date.getDayOfWeek().getValue() < 6;
    }

    public boolean isWeekend() {
        return date.getDayOfWeek().getValue() > 5;
    }

    @Override
    public int compareTo(VisitDate o) {
        return o.date.compareTo(date);
    }

}
