package christmas.domain;

import java.util.List;
import java.util.function.BiPredicate;

public enum Event {
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인", (date, order) -> date.isLessThan(26)),
    WEEKEND_DISCOUNT("주말 할인", (date, order) -> date.isWeekend()),
    WEEKDAY_DISCOUNT("평일 할인", (date, order) -> !date.isWeekend()),
    SPECIAL_DISCOUNT("특별 할인", (date, order) ->
            date.isContainedIn(List.of(3, 10, 17, 24, 25, 31))),
    GIFTS_EVENT("증정 이벤트", (date, order) -> order.calculateTotalPrice() >= 120000);

    private final String label;
    private final BiPredicate<Date, Order> condition;

    Event(String label, BiPredicate<Date, Order> condition) {
        this.label = label;
        this.condition = condition;
    }

    public boolean isMeetCondition(Date date, Order order) {
        return this.condition.test(date, order);
    }
}
