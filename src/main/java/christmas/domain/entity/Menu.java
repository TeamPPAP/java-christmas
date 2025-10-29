package christmas.domain.entity;

import christmas.domain.enums.MenuCategory;
import christmas.domain.enums.MenuItem;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Menu {

    public String getMenuListByCategoru(){
        String menu = null;
        Map<MenuCategory, List<String>> menuListByCategoru = Arrays.stream(MenuItem.values())
                .collect(Collectors.groupingBy(
                        item -> item.getCategory()
                        , Collectors.mapping(item -> item.toString(), Collectors.toList())
                ));


        return menu;
    }

}
