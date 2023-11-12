package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputViewTest {

    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @DisplayName("날짜 입력할 때, 숫자 이외의 다른 문자가 포함되어 있으면 에러 발생")
    @ValueSource(strings = {"hi", " ", "-"})
    @ParameterizedTest
    void validateTypeTest(String inputDate) {
        System.setIn(new ByteArrayInputStream(inputDate.getBytes()));

        assertThatThrownBy(() -> InputView.inputDate())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜 입력할 때, 1 이상 31 이하의 범위가 아닌 숫자를 입력하면 에러 발생")
    @ValueSource(strings = {"-5", "-35"})
    @ParameterizedTest
    void validateRangeTest(String inputDate) {
        System.setIn(new ByteArrayInputStream(inputDate.getBytes()));

        assertThatThrownBy(() -> InputView.inputDate())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
