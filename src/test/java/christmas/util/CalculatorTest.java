package christmas.util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static christmas.util.message.ErrorMessage.*;


public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    @DisplayName("이벤트 계산 테스트")
    void check_calculate_events() {
        Map<String, Integer> test = new HashMap<>();
        test.put("타파스", 2);
        test.put("양송이수프", 2);
        test.put("초코케이크", 3);
        Menu testMenu = new Menu(test);
        Date testDate = new Date(3);

        Map<Events, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put(Events.CHRISTMAS_D_DAY_DISCOUNT, 1200);
        expectedResult.put(Events.WEEKDAY_DISCOUNT, 6069);
        expectedResult.put(Events.SPECIAL_DISCOUNT, 1000);
        assertThat(calculator.calculateEvents(testDate, testMenu)).isEqualTo(expectedResult);

    }

    @Test
    @DisplayName("총 할인 금액 계산 테스트")
    void check_calculate_total_benefit_amount() {
        Map<Events, Integer> test = new LinkedHashMap<>();
        test.put(Events.CHRISTMAS_D_DAY_DISCOUNT, 1200);
        test.put(Events.WEEKDAY_DISCOUNT, 6069);
        test.put(Events.SPECIAL_DISCOUNT, 1000);
        assertThat(calculator.calculateTotalBenefitAmount(test) == 8069);

    }

    @Test
    @DisplayName("배지 계산 테스트")
    void check_calculate_total_amount_with_discount() {
        int totalBenefit = 8269;
        String expectedResult = "별";
        assertThat(calculator.calculateBadge(totalBenefit)).isEqualTo(expectedResult);

    }

}
