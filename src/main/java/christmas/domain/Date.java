package christmas.domain;

import java.util.List;

public class Date {
    private static final List<Integer> WEEKEND_FIRST_DAYS = List.of(1, 2);
    private static final int DAYS_IN_WEEK = 7;
    private final int date;

    private Date(int date) {
        this.date = date;
    }

    public static Date from(String inputDate) {
        return new Date(Integer.parseInt(inputDate));
    }

    public boolean isWeekend() {
        return WEEKEND_FIRST_DAYS.contains(date % DAYS_IN_WEEK);
    }

    public boolean isLessThan(int num) {
        return date < num;
    }

    public boolean isContainedIn(List<Integer> nums) {
        return nums.contains(date);
    }
}
