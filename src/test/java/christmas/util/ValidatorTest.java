package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static christmas.util.message.ErrorMessage.*;

public class ValidatorTest{

    Validator validator = new Validator();

    @Test
    @DisplayName("입력값이 null일 경우 예외가 발생한다.")
    void input_isNull() {
        String input = null;
        assertThatThrownBy(() -> validator.validateVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NULL_INPUT_ERROR.getMessage());
    }

    @Test
    @DisplayName("입력값이 비어있을 경우 예외가 발생한다.")
    void input_isEmpty() {
        String input = "";
        assertThatThrownBy(() -> validator.validateVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EXIST_OF_VALUE_ERROR.getMessage());
    }

    @Test
    @DisplayName("입력값이 1 이상 31 이하의 범위에 있지 않으면 예외가 발생한다.")
    void input_valid_range() {
        String input = " 32";
        assertThatThrownBy(() -> validator.validateVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE_ERROR.getMessage());
    }

    @Test
    @DisplayName("입력값이 0 이하일 경우 예외가 발생한다.")
    void check_negative_input() {
        String input = "-1";
        assertThatThrownBy(() -> validator.validateVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NEGATIVE_NUMBER_ERROR.getMessage());
    }

    @Test
    @DisplayName("입력값이 숫자가 아닐 경우 예외가 발생한다.")
    void check_numeric_input() {
        String input = "test";
        assertThatThrownBy(() -> validator.validateVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMERIC_INPUT_ERROR.getMessage());
    }

}
