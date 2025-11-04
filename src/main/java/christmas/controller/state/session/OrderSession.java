package christmas.controller.state.session;

import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import christmas.domain.model.Orders;

import java.util.ArrayList;
import java.util.List;

public final class OrderSession {
    private Integer date;
    private Menu selectedMenu;
    private final List<Order> orderList = new ArrayList<>();


    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(Menu selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order){
        orderList.add(order);
    }

    public void clearOrder(){
        orderList.clear();
    }

    public boolean isOrderListEmpty(){
        return !orderList.isEmpty();
    }

    public Orders toOrders(){
        return new Orders(orderList);
    }

    public boolean hasOrders() {
        return !orderList.isEmpty();
    }
}
