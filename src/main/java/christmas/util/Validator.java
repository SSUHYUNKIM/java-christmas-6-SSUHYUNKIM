package christmas.util;

import java.util.stream.Stream;

import static christmas.constant.NumericConstant.*;
import static christmas.util.ErrorMessage.*;

public class Validator {

    private static final String MENU_AMOUNT_SEPERATOR = "-";

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

    private static String removeSpacing(String inputValue) {
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
        if (!hasMenuAmountSeparator(inputValue)) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR.getMessage());
        }
    }

    private static boolean hasMenuAmountSeparator(String[] inputValue) {
        return Stream.of(inputValue)
                .anyMatch((input) -> input.contains(MENU_AMOUNT_SEPERATOR));
    }

    public static void checkValidOrder(String[] inputValue) {
        if (inputValue.length != 2 || isEmpty(inputValue[0]) || isEmpty(inputValue[1])) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR.getMessage());
        }
        checkIsInteger(inputValue[1]);
    }

    private static boolean isEmpty(String userInput) {
        return userInput.isEmpty();
    }

}
