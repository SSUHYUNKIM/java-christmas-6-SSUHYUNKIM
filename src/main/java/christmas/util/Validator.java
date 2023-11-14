package christmas.util;

import static christmas.constant.NumericConstant.*;
import static christmas.util.ErrorMessage.*;

public class Validator {

    private String removeSpacing(String inputValue) {
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

    private static void checkNegative(int inputValue) {
        if (inputValue < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_ERROR.getMessage());
        }
    }

    private static void checkDateRange(int date) {
        if (date > MAX_DATE_NUMBER || date < MIN_DATE_NUMBER) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR.getMessage());
        }
    }

}
