package christmas.model;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Menus {
    MUSHROOM_SOUP("애피타이저", "양송이수프", 6000),
    TAPAS("애피타이저", "타파스", 5500),
    CAESAR_SALAD("애피타이저", "시저샐러드", 8000),
    T_BONE_STEAK("메인", "티본스테이크", 55000),
    BARBECUE_LIB("메인", "바비큐립", 54000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25000),
    CHOCOLATE_CAKE("디저트", "초코케이크", 15000),
    ICE_CREAM("디저트", "아이스크림", 5000),
    ZERO_COLA("음료", "제로콜라", 3000),
    RED_WINE("음료", "레드와인", 60000),
    CHAMPAGNE("음료", "샴페인", 25000),
    NONE("none", "none", -1);

    private static final Map<String, Menus> menuNames =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Menus::getName, Function.identity())));

    private static final Map<Menus, String> menuNamesByMenus =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Function.identity(), Menus::getName)));
    private final String category;
    private final String name;
    private final int price;

    Menus(String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menus getMenusByName(String name) {
        return Optional.ofNullable(menuNames.get(name)).orElse(NONE);
    }

    public static String getNameByMenus(Menus menu) {
        return Optional.ofNullable(menuNamesByMenus.get(menu)).orElse(NONE.name);
    }

    public static int calculateEachPrice(Menus menu, int amount) {
        return menu.price * amount;
    }

    public String getCategory() {
        return category;
    }

    private String getName() {
        return name;
    }
}
