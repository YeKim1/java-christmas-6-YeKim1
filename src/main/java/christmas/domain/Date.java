package christmas.domain;

import java.util.List;

public class Date {
    private static final List<Integer> WEEKEND_FIRST_DAYS = List.of(1, 2);
    private static final List<Integer> SPECIAL_DISCOUNT_DAYS = List.of(3, 10, 17, 24, 25, 26, 31);
    private static final int DAYS = 7;

    private final int date;

    private Date(int date) {
        this.date = date;
    }

    public static Date from(String inputDate) {
        return new Date(Integer.parseInt(inputDate));
    }

    public boolean isWeekend() {
        return WEEKEND_FIRST_DAYS.contains(date % DAYS);
    }

    public boolean isSpecialDay() {
        return SPECIAL_DISCOUNT_DAYS.contains(date);
    }
}
