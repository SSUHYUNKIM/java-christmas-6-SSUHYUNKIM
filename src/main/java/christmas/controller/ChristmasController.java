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
        int date = inputView.getDate();
        return null;
    }

    private Menu getOrderMenu() {
        Map<String, Integer> menu = inputView.getMenu();
        return null;
    }
}
