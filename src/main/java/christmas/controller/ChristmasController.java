package christmas.controller;

import christmas.constant.EventConstant;
import christmas.util.Calculator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Event;
import christmas.model.Events;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<String, Integer> discount = new HashMap<>();

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();

        Date dateOfVisit = getDateOfVisit();
        Menu orderMenu = getOrderMenu();
        Event event = getEvent(dateOfVisit, orderMenu);
        GetPreviewEventBenefits(dateOfVisit, orderMenu, event);
    }

    private Date getDateOfVisit() {
        try {
            int date = inputView.getDate();
            return new Date(date);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDateOfVisit();
        }
    }

    private Menu getOrderMenu() {
        try {
            Map<String, Integer> menus = inputView.getMenu();
            Menu menu = new Menu(menus);
            discount.put(EventConstant.TOTAL_PRICE_BEFORE_DISCOUNT, menu.calculateTotalPriceBeforeDiscount());
            return menu;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getOrderMenu();
        }
    }

    private Event getEvent(Date dateOfVisit, Menu orderMenu) {
        Map<Events, Integer> events = new HashMap<>(Event.calculateEvents(dateOfVisit, orderMenu));
        discount.put(EventConstant.TOTAL_BENEFIT_AMOUNT, Calculator.calculateTotalBenefitAmount(events));
        discount.put(EventConstant.TOTAL_PRICE_AFTER_DISCOUNT, Calculator.calculateTotalAmountWithDiscount(discount, events));
        return new Event(dateOfVisit, orderMenu, events);
    }

    private void GetPreviewEventBenefits(Date dateOfVisit, Menu orderMenu, Event event) {
        getVisitingPreview(dateOfVisit);
        getOrderMenuPreview(event);
        getTotalPriceBeforeDiscount(orderMenu);
        getGiveawayMenu(event);
        getBenefitDetails(event);
        getTotalBenefitAmount();
        getExpectedAmountAfterDiscount();
        getEventBadge();
    }

    private void getVisitingPreview(Date dateOfVisit) {
        int visitingDate = dateOfVisit.getDate();
        outputView.printEventPreviewMessage(visitingDate);
    }

    private void getOrderMenuPreview(Event event) {
        List<String> orderedMenuInfo = event.provideOrderedMenusInfo();
        outputView.printOrderMenuMessage(orderedMenuInfo);
    }

    private void getTotalPriceBeforeDiscount(Menu orderMenu) {
        int totalPriceBeforeDiscount = orderMenu.getTotalPriceBeforeDiscount();
        outputView.printTotalPriceBeforeDiscountMessage(totalPriceBeforeDiscount);
    }

    private void getGiveawayMenu(Event event) {
        String giveawayMenu = Calculator.calculateGiveawayMenu(event.getBenefitDetails());
        outputView.printGiveawayMenuMessage(giveawayMenu);
    }

    private void getBenefitDetails(Event event) {
        List<String> benefitDetails = event.getBenefitDetails();
        outputView.printBenefitDetailsMessage(benefitDetails);
    }

    private void getTotalBenefitAmount() {
        int totalBenefitsAmount = discount.get(EventConstant.TOTAL_BENEFIT_AMOUNT);
        outputView.printTotalBenefitsAmountMessage(totalBenefitsAmount);
    }

    private void getExpectedAmountAfterDiscount() {
        int discountedTotalPrice = discount.get(EventConstant.TOTAL_PRICE_AFTER_DISCOUNT);
        outputView.printExpectedAmountAfterDiscountMessage(discountedTotalPrice);
    }

    private void getEventBadge() {
        String badge = Calculator.calculateBadge(discount.get(EventConstant.TOTAL_BENEFIT_AMOUNT) * -1);
        outputView.printEventBadgeMessage(badge);
    }
}
