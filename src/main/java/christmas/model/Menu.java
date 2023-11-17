package christmas.model;

import java.util.*;

import christmas.constant.EventConstant;
import christmas.util.Validator;

public class Menu {
    private final Map<Menus, Integer> orderedMenus;
    private int totalPriceBeforeDiscount = 0;
    private List<String> categories = new ArrayList<>();

    public Menu(Map<String, Integer> menus) {
        Validator.checkOrderedMenu(menus);
        Map<Menus, Integer> orderedMenus = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> menu : menus.entrySet()) {
            Menus orderedMenuName = Menus.getMenusByName(menu.getKey());
            Integer orderedMenuAmount = menu.getValue();
            orderedMenus.put(orderedMenuName, orderedMenuAmount);
        }
        this.orderedMenus = Map.copyOf(orderedMenus);
    }

    public int calculateTotalPriceBeforeDiscount() {
        for (Map.Entry<Menus, Integer> orderedMenu : orderedMenus.entrySet()) {
            totalPriceBeforeDiscount += Menus.calculateEachPrice(orderedMenu.getKey(), orderedMenu.getValue());
        }
        return totalPriceBeforeDiscount;
    }

    public List<String> provideMenuCategories() {
        for (Menus menus : orderedMenus.keySet()) {
            categories.add(menus.getCategory());
        }
        return categories;
    }

    public int getTotalPriceBeforeDiscount() {
        return this.totalPriceBeforeDiscount;
    }

    public int getDessertAmount() {
        int dessertAmount = 0;
        for (Menus menu : orderedMenus.keySet()) {
            if (menu.getCategory().equals(EventConstant.DESSERT_CATEGORY)) {
                dessertAmount += orderedMenus.get(menu);
            }
        }
        return dessertAmount;
    }

    public int getMainAmount() {
        int mainAmount = 0;
        for (Menus menus : orderedMenus.keySet()) {
            if (menus.getCategory().equals(EventConstant.MAIN_CATEGORY)) {
                mainAmount += orderedMenus.get(menus);
            }
        }
        return mainAmount;
    }

    public List<String> getOrderedMenusInfo() {
        List<String> orderedMenusInfo = new ArrayList<>();
        for (Map.Entry<Menus, Integer> orderedMenu : orderedMenus.entrySet()) {
            String orderedMenuInfo = Menus.getNameByMenus(orderedMenu.getKey())+ " " + orderedMenu.getValue() + EventConstant.COUNT_UNIT;
            orderedMenusInfo.add(orderedMenuInfo);
        }
        return orderedMenusInfo;
    }
}
