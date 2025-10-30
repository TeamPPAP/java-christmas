package christmas.service;

import christmas.domain.model.Menu;
import christmas.repository.MenuRepository;
import java.util.List;

public class MenuService {
    MenuRepository menuRepo;

    public MenuService(MenuRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    public List<Menu> getAllMenu() {
        return menuRepo.getMenuList();
    }
}
