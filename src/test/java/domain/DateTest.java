package domain;

import christmas.domain.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DateTest {

    @DisplayName("숫자 이외의 다른 문자가 포함되어 있으면 에러 발생")
    @Test
    void validateTypeTest() {
        String testDate = "hi";
        assertThatThrownBy(() -> Date.from(testDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1 이상 31 이하의 범위가 아닌 숫자를 입력하면 에러 발생")
    @Test
    void validateRangeTest() {
        String testDate = "-5";
        assertThatThrownBy(() -> Date.from(testDate))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
