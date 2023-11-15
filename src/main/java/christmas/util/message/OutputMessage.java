package christmas.util.message;

public enum OutputMessage {
    OUT_WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

