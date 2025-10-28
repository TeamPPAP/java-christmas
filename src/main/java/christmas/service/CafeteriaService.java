package christmas.service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class CafeteriaService {

    private Map<String, Integer> getCollect(List<String> order) {
        return order.stream()
            .map(s -> s.split("-"))
            .collect(groupingBy(
                s -> s[0],
                summingInt(s -> Integer.parseInt(s[1]))
            ));
    }
}
