package christmas.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import christmas.constant.EventConstant;
import christmas.constant.NumericConstant;
import christmas.model.*;

public class Calculator {

    public static Map<Events, Integer> calculateEvents(Date dateOfVisit, Menu orderMenu) {
        Map<Events, Integer> applicableEvents = new HashMap<>();
        calculateDiscount(dateOfVisit, orderMenu, applicableEvents);
        calculateGiftEvent(orderMenu, applicableEvents);
        return applicableEvents;
    }

    public static int calculateTotalBenefitAmount(Map<Events, Integer> events) {
        int totalBenefitAmount = 0;
        for (Events event : events.keySet()) {
            totalBenefitAmount += events.get(event);
        }
        totalBenefitAmount *=-1;
        return totalBenefitAmount;
    }

    public static int calculateTotalAmountWithDiscount(Map<String, Integer> discount, Map<Events, Integer> events) {
        int discountedTotalAMount = discount.get("할인 전 총주문 금액") + discount.get("총혜택 금액");
        if (events.containsKey(Events.GIFT_EVENT)) {
            discountedTotalAMount += NumericConstant.GIFT_EVENT_DISCOUNT_AMOUNT;
        }
        return discountedTotalAMount;
    }

    public static List<String> calculateEventsInfo(Map<Events, Integer> events) {
        List<String> eventsInfo = new ArrayList<>();
        if (events.isEmpty()) {
            return List.of(EventConstant.NONE);
        }
        for (Map.Entry<Events, Integer> eventResult : events.entrySet()) {
            String eventName = Events.getEventName(eventResult.getKey());
            String benefitAmount = new DecimalFormat(EventConstant.PRICE_PATTERN)
                    .format(events.get(eventResult.getKey()) * -1);
            eventsInfo.add(eventName + ": " + benefitAmount + EventConstant.AMOUNT_UNIT);
        }
        return eventsInfo;
    }

    public static String calculateGiveawayMenu(List<String> events) {
        for (String event : events) {
            if (event.contains(Events.getEventName(Events.GIFT_EVENT))) {
                return "샴페인 " + 1 + EventConstant.COUNT_UNIT;
            }
        }
        return EventConstant.NONE;
    }

    public static String calculateBadge(int totalBenefitsAmount) {
        if (totalBenefitsAmount >= 20000) {
            return Badges.SANTA.badgeName();
        }
        if (totalBenefitsAmount >= 10000) {
            return Badges.TREE.badgeName();
        }
        if (totalBenefitsAmount >= 5000) {
            return Badges.STAR.badgeName();
        }
        return EventConstant.NONE;
    }

    private static void calculateDiscount(Date dateOfVisit, Menu orderMenu, Map<Events, Integer> applicableEvents) {
        applyChristmasDiscount(dateOfVisit, applicableEvents);
        applyWeekdayDiscount(dateOfVisit, orderMenu, applicableEvents);
        applyWeekendDiscount(dateOfVisit, orderMenu, applicableEvents);
        applySpecialDiscount(dateOfVisit, applicableEvents);
    }

    private static void applyChristmasDiscount(Date dateOfVisit, Map<Events, Integer> applicableEvents) {
        if (dateOfVisit.isChristmasDiscountPeriod()) {
            int discountAmount = NumericConstant.CHRISTMAS_BASIC_DISCOUNT_AMOUNT
                    + (NumericConstant.CHRISTMAS_ADDITIONAL_DISCOUNT_UNIT * dateOfVisit.calculateLastDate());
            applicableEvents.put(Events.CHRISTMAS_D_DAY_DISCOUNT, discountAmount);
        }
    }

    private static void applyWeekdayDiscount(Date dateOfVisit, Menu orderMenu, Map<Events, Integer> applicableEvents) {
        if (dateOfVisit.isWeekday()) {
            int discountAmount = NumericConstant.WEEKDAY_DISCOUNT_AMOUNT * orderMenu.getDessertAmount();
            applicableEvents.put(Events.WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    private static void applyWeekendDiscount(Date dateOfVisit, Menu orderMenu, Map<Events, Integer> applicableEvents) {
        if (dateOfVisit.isWeekend()) {
            Integer discountAmount = NumericConstant.WEEKEND_DISCOUNT_AMOUNT * orderMenu.getMainAmount();
            applicableEvents.put(Events.WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private static void applySpecialDiscount(Date dateOfVisit, Map<Events, Integer> applicableEvents) {
        if (dateOfVisit.isSpecial()) {
            applicableEvents.put(Events.SPECIAL_DISCOUNT, NumericConstant.SPECIAL_DISCOUNT_AMOUNT);
        }
    }

    private static void calculateGiftEvent(Menu orderMenu, Map<Events, Integer> applicableEvents) {
        if (orderMenu.getTotalPriceBeforeDiscount() >= NumericConstant.MINIMUM_GIFT_EVENT_AMOUNT) {
            applicableEvents.put(Events.GIFT_EVENT, NumericConstant.GIFT_EVENT_DISCOUNT_AMOUNT);
        }
    }


}
