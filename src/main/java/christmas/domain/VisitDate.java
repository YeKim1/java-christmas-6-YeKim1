package christmas.domain;

import java.util.List;

public class VisitDate {
    private static final List<Integer> WEEKEND_FIRST_DAYS = List.of(1, 2);
    private static final int DAYS_IN_WEEK = 7;

    private final int date;

    private VisitDate(int date) {
        this.date = date;
    }

    public static VisitDate from(String inputDate) {
        return new VisitDate(Integer.parseInt(inputDate));
    }

    public int getDate() {
        return date;
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

    public int subtractDate(int num) {
        return date - num;
    }
}
