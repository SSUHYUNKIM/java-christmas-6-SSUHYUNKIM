package christmas.util;

import christmas.model.Menus;

import java.util.Map;
import java.util.stream.Stream;

import static christmas.constant.NumericConstant.*;
import static christmas.util.message.ErrorMessage.*;

public class Validator {

    private static final String MENU_AMOUNT_SEPARATOR = "-";

    public static void validateVisitDate(String inputValue) {
        checkNull(inputValue);
        inputValue = removeSpacing(inputValue);
        checkExistOfValue(inputValue);
        checkNegative(inputValue);
        checkDateRange(inputValue);
    }
    public static void checkIsInteger(String inputValue) {
        try {
            Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMERIC_INPUT_ERROR.getMessage());
        }
    }

    public static String removeSpacing(String inputValue) {
        return inputValue.replaceAll(" ", "");
    }

    private static void checkNull(String inputValue) {
        if(inputValue == null) {
            throw new IllegalArgumentException(NULL_INPUT_ERROR.getMessage());
        }
    }
    private static void checkExistOfValue(String inputValue) {
        if (isEmpty((inputValue))) {
            throw new IllegalArgumentException(EXIST_OF_VALUE_ERROR.getMessage());
        }
    }

    private static void checkNegative(String inputValue) {
        checkIsInteger(inputValue);
        int num = Integer.parseInt(inputValue);
        if (num < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_ERROR.getMessage());
        }
    }

    private static void checkDateRange(String inputValue) {
        checkIsInteger(inputValue);
        int date = Integer.parseInt(inputValue);
        if (date > MAX_DATE_NUMBER || date < MIN_DATE_NUMBER) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR.getMessage());
        }
    }

    public static void checkMenuAmountSeparator(String[] inputValue) {
        if (hasNotMenuAmountSeparator(inputValue)) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR.getMessage());
        }
    }

    private static boolean hasNotMenuAmountSeparator(String[] inputValue) {
        return Stream.of(inputValue)
                .anyMatch((input) -> !input.contains(MENU_AMOUNT_SEPARATOR));
    }

    public static void checkValidOrder(String[] inputValue) {
        if (inputValue.length != 2 || isEmpty(inputValue[0]) || isEmpty(inputValue[1])) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR.getMessage());
        }
        checkValidAmount(inputValue[1]);
    }

    public static void checkValidAmount(String inputValue) {
        try {
            Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR.getMessage());
        }
    }

    private static boolean isEmpty(String userInput) {
        return userInput.isEmpty();
    }

    //여기서부터 추가 test 필요
    public static void checkOrderedMenu(Map<String, Integer> inputValues) {
        int totalAmount = 0;
        for (Map.Entry<String, Integer> inputValue : inputValues.entrySet()) {
            if (isNotExistOnMenu(inputValue.getKey()) || isReachMinimumAmount(inputValue.getValue())) {
                throw new IllegalArgumentException(INVALID_ORDER_ERROR.getMessage());
            }
            totalAmount += inputValue.getValue();
        }
        if (isExceedMaximumAmount(totalAmount)) {
            throw new IllegalArgumentException(EXCEED_MAXIMUM_ERROR.getMessage());
        }
    }

    private static boolean isNotExistOnMenu(String menuName) {
        return Menus.getMenusByName(menuName).equals(Menus.NONE);
    }

    private static boolean isReachMinimumAmount(int amount) {
        return amount < 1;
    }
    private static boolean isExceedMaximumAmount(int totalAmount) {
        return totalAmount > 20;
    }

}
