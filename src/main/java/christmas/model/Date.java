package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import christmas.constant.NumericConstant;

import static christmas.constant.NumericConstant.MIN_DATE_NUMBER;

public class Date {
    private final int date;

    public Date(int date) {
        this.date = date;
    }

    public int calculateLastDate() {
        return date - MIN_DATE_NUMBER;
    }

    public boolean isChristmasDiscountPeriod() {
        return date >= NumericConstant.CHRISTMAS_DISCOUNT_START_DATE && date <= NumericConstant.CHRISTMAS_DISCOUNT_END_DATE;
    }

    public boolean isWeekday() {
        return getDateInfo().contains(Day.WEEKDAY.name());
    }

    public boolean isWeekend() {
        return getDateInfo().contains(Day.WEEKEND.name());
    }

    public boolean isSpecial() {
        return getDateInfo().contains(Day.SPECIAL.name());
    }

    public List<String> getDateInfo() {
        List<String> dateInfo = new ArrayList<>();
        Set<Day> appliedDays = Day.calculateDate(date);
        for (Day appliedDay : appliedDays) {
            dateInfo.add(appliedDay.name());
        }
        dateInfo.add(String.valueOf(date));
        return dateInfo;
    }

    public int getDate() {
        return this.date;
    }
}
