package christmas.service;

import christmas.repository.MenuRepository;

public class OrderService {
    private MenuRepository menuRepo;

    public OrderService(MenuRepository menuRepo) {
        this.menuRepo = menuRepo;
    }
}
