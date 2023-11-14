package domain;

import christmas.domain.VisitDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VisitDateTest {

    @DisplayName("date 값이 주말인지 계산하는 메서드 테스트")
    @Test
    void isWeekendTest() {
        VisitDate visitDate = VisitDate.from("22");

        assertThat(true).isEqualTo(visitDate.isWeekend());
    }

    @DisplayName("date 값이 주어진 날짜보다 작은지 검사하는 메서드 테스트")
    @Test
    void isLessThanTest() {
        VisitDate visitDate = VisitDate.from("4");

        assertThat(false).isEqualTo(visitDate.isLessThan(4));
    }

    @DisplayName("date 값이 주어진 List 안에 속하는지 검사하는 메서드 테스트")
    @Test
    void isContainedInTest() {
        VisitDate visitDate = VisitDate.from("5");

        assertThat(true).isEqualTo(visitDate.isContainedIn(List.of(1, 2, 3, 4, 5)));
    }
}
