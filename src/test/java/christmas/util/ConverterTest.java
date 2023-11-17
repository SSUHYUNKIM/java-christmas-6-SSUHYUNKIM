package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static christmas.util.message.ErrorMessage.*;

public class ConverterTest {

    Converter converter = new Converter();

    @Test
    @DisplayName("요구하는 형식에 맞지 않는 주문일 경우 예외가 발생한다. - 1")
    void check_valid_order_format_1() {
        String input = "티본스테이크1,시저샐러드-2";
        assertThatThrownBy(() -> converter.convertStringToMap(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR.getMessage());
    }

    @Test
    @DisplayName("요구하는 형식에 맞지 않는 주문일 경우 예외가 발생한다. - 2")
    void check_valid_order_format_2() {
        String input = "시저샐러드,";
        assertThatThrownBy(() -> converter.convertStringToMap(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR.getMessage());
    }

    @Test
    @DisplayName("요구하는 형식에 맞지 않는 주문일 경우 예외가 발생한다. - 3")
    void check_valid_order_format_3() {
        String input = "시저샐러드,2,2";
        assertThatThrownBy(() -> converter.convertStringToMap(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR.getMessage());
    }

    @Test
    @DisplayName("요구하는 형식에 맞지 않는 주문일 경우 예외가 발생한다. - 4")
    void check_valid_order_format_4() {
        String input = ",시저샐러드";
        assertThatThrownBy(() -> converter.convertStringToMap(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR.getMessage());
    }

    @Test
    @DisplayName("요구하는 형식에 맞지 않는 주문일 경우 예외가 발생한다. - 5")
    void check_valid_order_format_5() {
        String input = "시저샐러드-1,시저샐러드-2";
        assertThatThrownBy(() -> converter.convertStringToMap(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR.getMessage());
    }
}
