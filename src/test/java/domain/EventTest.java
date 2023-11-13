package domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.VisitDate;
import christmas.domain.Event;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {

    @DisplayName("할인 여부를 검사하는 메서드 테스트")
    @Test
    void isMeetConditionTest() {
        VisitDate visitDate = VisitDate.from("1");
        Order order = Order.from("티본스테이크-5");

        Event event1 = Event.GIFTS_EVENT;
        Event event2 = Event.WEEKDAY_DISCOUNT;

        assertThat(true)
                .isEqualTo(event1.isMeetCondition(visitDate, order));
        assertThat(false)
                .isEqualTo(event2.isMeetCondition(visitDate, order));
    }
}
