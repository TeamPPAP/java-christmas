package christmas.domain.entity;

import christmas.domain.enums.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class Order {
    Map<MenuItem, Integer> quantityByMenuItem;

    //TODO: 정적팩토리 메서드로 변환에 대해 검토
    //TODO: stream 적용해서 바꿔보기
    public void parseMenuList(String menuLine) {
        String menuLineTrimed = menuLine.replace(" ", "");
        String[] menuAndStockArray = menuLineTrimed.split(",");
        Map<MenuItem, Integer> menuQuantityMap = new HashMap<MenuItem, Integer>();

        for (String s : menuAndStockArray) {
           String[] a = s.split("-");
           MenuItem key = MenuItem.of(a[0]);
           int count = Integer.parseInt(a[1]);
           menuQuantityMap.put(key, count);
        }

        if(menuQuantityMap.size() == 0)
            throw new IllegalArgumentException("주문이 정상적으로 이루어지지않았습니다.");

        quantityByMenuItem = menuQuantityMap;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (MenuItem menuItem : quantityByMenuItem.keySet()) {
            totalPrice += menuItem.getPrice() * quantityByMenuItem.get(menuItem);
        }
        return totalPrice;
    }


}
