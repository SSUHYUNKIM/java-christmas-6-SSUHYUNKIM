package christmas.util.message;

public enum OutputMessage {
    OUT_WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    OUT_PREVIEW("12월 " + "%d" + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

