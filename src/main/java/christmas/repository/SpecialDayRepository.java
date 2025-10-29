package christmas.repository;

import java.util.ArrayList;
import java.util.List;

public class SpecialDayRepository {

    public List<Integer> listSpecialDay() {
        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(14);
        list.add(21);
        list.add(25);
        list.add(28);

        return list;
    }
}
