package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import christmas.util.message.InputMessage;
import christmas.util.Validator;
import christmas.util.Converter;

import java.util.HashMap;
import java.util.Map;

public class InputView {
    public int getDate() {
        System.out.println(InputMessage.INPUT_DATE_OF_VISIT.getMessage());
        try {
            String input = Console.readLine();
            Validator.validateVisitDate(input);
            input = Validator.removeSpacing(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDate();
        }
    }

    public Map<String, Integer> getMenu() {
        System.out.println(InputMessage.INPUT_ORDER_MENU_AND_AMOUNT.getMessage());
        try {
            String input = Console.readLine();
            input = Validator.removeSpacing(input);
            return Converter.convertStringToMap(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMenu();
        }
    }
}
