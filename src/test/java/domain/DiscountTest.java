package domain;

import christmas.domain.VisitDate;
import christmas.domain.Discount;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountTest {

    @DisplayName("총혜택 금액 계산 테스트")
    @Test
    void getSumOfDiscount() {
        Discount discount = Discount.of(VisitDate.from("1"), Order.from("티본스테이크-3,바비큐립-5,제로콜라-1"));

        assertThat(discount.getSumOfDiscount()).isEqualTo(42184);
    }
}
