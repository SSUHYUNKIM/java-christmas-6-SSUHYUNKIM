package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import christmas.util.message.InputMessage;
import christmas.util.Validator;

public class InputView {
    public int getDate() {
        System.out.println(InputMessage.INPUT_DATE_OF_VISIT.getMessage());
        try {
            String input = Console.readLine();
            Validator.validateVisitDate(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDate();
        }
    }
}
