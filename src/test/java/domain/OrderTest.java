package domain;

import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    @DisplayName("총주문 금액을 계산하는 메소드 테스트")
    @Test
    void calculateTotalPriceTest() {
        int answer = 73000;

        Order order = Order.from("해산물파스타-2,제로콜라-1");

        assertThat(answer).isEqualTo(order.calculateTotalPrice());
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력한 경우 예외 발생")
    @Test
    void validateExistedMenuTest() {
        assertThatThrownBy(() -> Order.from("hi-3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문한 경우 예외 발생")
    @Test
    void validateOnlyBeveragesTest() {
        assertThatThrownBy(() -> Order.from("제로콜라-3"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
