package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.model.Date;
import christmas.model.Menu;

import java.util.Map;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();

        Date dateOfVisit = getDateOfVisit();
        Menu orderMenu = getOrderMenu();
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
            return new Menu(menus);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getOrderMenu();
        }
    }
}
