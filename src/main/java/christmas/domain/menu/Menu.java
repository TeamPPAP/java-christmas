package christmas.domain.menu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Menu {

    public String getMenuListByCategory() {
        String menu = Arrays.stream(MenuItem.values())
                .sorted(Comparator.comparing(entry -> entry.getCategory().ordinal()))
                .collect(Collectors.groupingBy(
                        item -> item.getCategory()
                        , Collectors
                                .mapping(
                                        MenuItem::toString,       // 문자열로 변환
                                        Collectors.joining(", ")  // 리스트 대신 문자열로 합치기
                                )
                )).entrySet().stream()
                .map(entry -> String.format("%s\n%s", entry.getKey().toString(), entry.getValue()))
                .collect(Collectors.joining("\n\n")); // 카테고리 구분은 줄바꿈 2번

        return menu;
    }

}
