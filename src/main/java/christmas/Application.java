package christmas;

import christmas.controller.RestaurantController;
import christmas.init.InitController;

public class Application {
    public static void main(String[] args) {
        InitController init = new InitController();
        RestaurantController controller = init.forCreateController();

        controller.run();
    }
}
