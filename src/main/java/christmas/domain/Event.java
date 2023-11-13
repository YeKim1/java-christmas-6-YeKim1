package christmas.domain;
;
import java.util.function.BiPredicate;

public enum Event {
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    GIFTS_EVENT("증정 이벤트");

    String label;

    Event(String label) {
        this.label = label;
    }
}
