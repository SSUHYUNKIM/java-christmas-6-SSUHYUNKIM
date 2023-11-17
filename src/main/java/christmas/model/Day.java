package christmas.model;

import java.util.*;

import christmas.constant.NumericConstant;

public enum Day {
    WEEKDAY("평일", Arrays.asList(3, 4, 5, 6, 0)),
    WEEKEND("주말", Arrays.asList(1, 2)),
    SPECIAL("특별", List.of(3)),
    BASIC("기본", List.of());

    private final String category;
    private final List<Integer> includedDate;


    Day(String category, List<Integer> includedDate) {
        this.category = category;
        this.includedDate = includedDate;
    }

    public static Set<Day> calculateDate(Integer date) {
        Set<Day> appliedDays = new LinkedHashSet<>();
        if (Objects.equals(date, NumericConstant.CHRISTMAS_DATE)) {
            appliedDays.add(SPECIAL);
        }
        int dividedDate = date % NumericConstant.DAYS_OF_WEEK;
        List<Day> findDays = Arrays.stream(Day.values())
                .filter((days) -> days.hasDividedDate(dividedDate))
                .toList();
        appliedDays.addAll(findDays);
        return appliedDays;
    }

    private boolean hasDividedDate(Integer dividedDate) {
        return includedDate.stream()
                .anyMatch(includedDate -> includedDate.equals(dividedDate));
    }
}
