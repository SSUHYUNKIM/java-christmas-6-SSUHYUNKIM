package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.model.Date;

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
    }

    private Date getDateOfVisit() {
        int date = inputView.getDate();
        return null;
    }
}
