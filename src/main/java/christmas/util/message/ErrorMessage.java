package christmas.util.message;

public enum ErrorMessage {
    NULL_INPUT_ERROR("null은 입력할 수 없습니다."),
    EXIST_OF_VALUE_ERROR("입력 값이 존재하지 않습니다."),
    NUMERIC_INPUT_ERROR("숫자만 입력이 가능합니다."),
    INVALID_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NEGATIVE_NUMBER_ERROR("0보다 큰 숫자만 입력이 가능합니다."),
    INVALID_ORDER_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ALREADY_EXIST_ERROR("총 메뉴의 개수는 20개 이하여야 합니다."),
    ORDER_ONLY_DRINKS_ERROR("음료만 주문할 수 없습니다.");

    private final static String ERROR_MESSAGE_HEADER = "[ERROR] %s";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format(ERROR_MESSAGE_HEADER, message);
    }

}

