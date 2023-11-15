package christmas.util;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import static christmas.util.message.ErrorMessage.INVALID_ORDER_ERROR;

public class Converter {
    private final String MENU_SEPERATOR = ",";
    private final String MENU_AMOUNT_SEPERATOR = "-";

    public Map<String, Integer> convertStringToMap(String inputValue) {
        List<String> input = convertStringToStringList(inputValue);
        return convertStringListToMap(input);
    }

    private List<String> convertStringToStringList(String inputValue) {
        String[] split = inputValue.split(MENU_SEPERATOR);
        Validator.checkMenuAmountSeparator(split);
        return Stream.of(split)
                .collect(Collectors.toList());
    }

    private Map<String, Integer> convertStringListToMap(List<String> inputValue) {
        Map<String, Integer> inputValues = new HashMap<>();
        List<String[]> orders = separateMenuAndAmount(inputValue);
        for (var order : orders) {
            Validator.checkValidOrder(order);
            inputValues.put(order[0], Integer.parseInt(order[1]));
        }
        if (inputValue.size() != inputValues.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR.getMessage());
        }
        return inputValues;
    }

    private List<String[]> separateMenuAndAmount(List<String> order) {
        return order.stream()
                .map((menuOrder) -> menuOrder.split(MENU_AMOUNT_SEPERATOR))
                .collect(Collectors.toList());
    }

    private boolean hasMenuAmountSeparator(String[] menuOrders) {
        return Stream.of(menuOrders)
                .anyMatch((menuOrder) -> menuOrder.contains(MENU_AMOUNT_SEPERATOR));
    }
}
