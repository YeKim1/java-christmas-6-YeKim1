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
    void validateDateTypeTest(String inputDate) {
        System.setIn(new ByteArrayInputStream(inputDate.getBytes()));

        assertThatThrownBy(() -> InputView.inputDate())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜 입력할 때, 1 이상 31 이하의 범위가 아닌 숫자를 입력하면 에러 발생")
    @ValueSource(strings = {"-5", "-35"})
    @ParameterizedTest
    void validateDateRangeTest(String inputDate) {
        System.setIn(new ByteArrayInputStream(inputDate.getBytes()));

        assertThatThrownBy(() -> InputView.inputDate())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 입력할 때, 메뉴의 개수에 숫자 이외의 다른 문자가 포함되어 있으면 에러 발생")
    @ValueSource(strings = {
            "티본스테이크-1,바비큐립-hi,초코케이크-3",
            "제로콜라-1,타파스- ,초코케이크-1",
            "제로콜라-1,타파스-@,초코케이크-3"
    })
    @ParameterizedTest
    void validateOrderCountTypeTest(String inputOrder) {
        System.setIn(new ByteArrayInputStream(inputOrder.getBytes()));

        assertThatThrownBy(() -> InputView.inputOrder())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 입력할 때, 메뉴의 개수가 1보다 작은 숫자면 에러 발생")
    @ValueSource(strings = {
            "티본스테이크-1,바비큐립-0,초코케이크-3",
    })
    @ParameterizedTest
    void validateOrderCountRangeTest(String inputOrder) {
        System.setIn(new ByteArrayInputStream(inputOrder.getBytes()));

        assertThatThrownBy(() -> InputView.inputOrder())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 입력할 때, 중복된 메뉴를 입력하면 에러 발생")
    @ValueSource(strings = {
            "티본스테이크-1,티본스테이크-4,초코케이크-3",
    })
    @ParameterizedTest
    void validateDuplicatedMenuNameTest(String inputOrder) {
        System.setIn(new ByteArrayInputStream(inputOrder.getBytes()));

        assertThatThrownBy(() -> InputView.inputOrder())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 입력할 때, 총 개수가 20개를 넘으면 에러 발생")
    @ValueSource(strings = {
            "티본스테이크-15,초코케이크-7",
    })
    @ParameterizedTest
    void validateOverMenuCountSumTest(String inputOrder) {
        System.setIn(new ByteArrayInputStream(inputOrder.getBytes()));

        assertThatThrownBy(() -> InputView.inputOrder())
                .isInstanceOf(IllegalArgumentException.class);
    }


}
