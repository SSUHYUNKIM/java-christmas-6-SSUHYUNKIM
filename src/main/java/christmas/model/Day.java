package christmas.model;

import java.util.Arrays;
import java.util.List;

public enum Day {
    WEEKDAY("평일", Arrays.asList(4, 5, 6, 7)),
    WEEKEND("주말", Arrays.asList(1, 2)),
    SPECIAL("특별", Arrays.asList(3, 25)),
    BASIC("기본", List.of());

    private final String category;
    private final List<Integer> includedDate;


    Day(String category, List<Integer> includedDate) {
        this.category = category;
        this.includedDate = includedDate;
    }
}
