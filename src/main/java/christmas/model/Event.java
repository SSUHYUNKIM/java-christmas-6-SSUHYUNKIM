package christmas.model;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import christmas.constant.EventConstant;
import christmas.constant.NumericConstant;
import christmas.util.Calculator;

public class Event {

    private final Map<String, List<String>> orderInfo;

    public Event(Date dateOfVisit, Menu orderMenu, Map<Events, Integer> events) {
        Map<String, List<String>> orderInfo = new HashMap<>();
        List<String> orderedMenusInfo = List.copyOf(orderMenu.getOrderedMenusInfo());
        List<String> eventsInfo = Calculator.calculateEventsInfo(events);
        orderInfo.put(EventConstant.ORDER_MENU, orderedMenusInfo);
        orderInfo.put(EventConstant.BENEFIT_AMOUNT, eventsInfo);
        this.orderInfo = Map.copyOf(orderInfo);
    }
    public static Map<Events, Integer> calculateEvents(Date dateOfVisit, Menu orderMenu) {
        if (!isPossibleToParticipate(orderMenu)) {
            return new HashMap<>();
        }
        return Calculator.calculateEvents(dateOfVisit, orderMenu);
    }

    public List<String> provideOrderedMenusInfo() {
        return orderInfo.get(EventConstant.ORDER_MENU);
    }

    public List<String> getBenefitDetails() {
        return orderInfo.get(EventConstant.BENEFIT_AMOUNT);
    }

    private static boolean isPossibleToParticipate(Menu menu) {
        int totalPriceBeforeDiscount = menu.getTotalPriceBeforeDiscount();
        return !isLessThanMinimumPurchaseAmount(totalPriceBeforeDiscount) && !isOnlyBeverage(menu);
    }

    private static boolean isOnlyBeverage(Menu menu) {
        List<String> categories = menu.provideMenuCategories();
        categories.remove(EventConstant.BEVERAGE_CATEGORY);
        return categories.isEmpty();
    }

    private static boolean isLessThanMinimumPurchaseAmount(int totalAmount) {
        return totalAmount < NumericConstant.MINIMUM_PURCHASE_AMOUNT;
    }
}
