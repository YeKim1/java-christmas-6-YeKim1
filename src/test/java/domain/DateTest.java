package domain;

import christmas.domain.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTest {

    @DisplayName("날짜가 평일인지 주말인지 확인하는 기능")
    @Test
    void isWeekendTest() {
        Date date = Date.from("4");

        assertThat(false).isEqualTo(date.isWeekend());
    }
}
