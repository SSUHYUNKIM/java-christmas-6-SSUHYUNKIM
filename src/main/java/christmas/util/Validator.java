package christmas.util;

import static christmas.constant.NumericConstant.*;
import static christmas.util.ErrorMessage.*;

public class Validator {

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
        if (inputValue.isEmpty()) {
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

}
