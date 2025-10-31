package christmas.domain.model;

import christmas.domain.model.constant.Category;

import static christmas.domain.model.message.ErrorMessage.*;

public record Menu(String menuName, int price, Category category) {
    public Menu {
        if(menuName == null || menuName.isBlank()){
            throw new IllegalArgumentException(MENU_NAME_IS_BLANK.getMessage());
        }
        if(price<0){
            throw new IllegalArgumentException(MENU_PRICE_UNDER_ZERO.getMessage());
        }
        if(category == null){
            throw new IllegalArgumentException(MENU_CATEGORY_IS_BLANK.getMessage());
        }
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.equals(this.menuName);
    }
}

