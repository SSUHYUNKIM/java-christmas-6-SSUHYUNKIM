package christmas.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import christmas.model.Menus;
import christmas.util.Validator;

public class Menu {
    private final Map<Menus, Integer> orderedMenus;

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
}
