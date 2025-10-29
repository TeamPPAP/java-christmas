package christmas.service;

import christmas.domain.enums.MenuItem;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EventPlannerService {

    public String getMenuList(){
        Arrays.stream(MenuItem.values())
                .collect(Collectors.toMap(
                        item -> item.getCategory(),
                        item -> item.toString()
                ));

        return "";
    }
}
