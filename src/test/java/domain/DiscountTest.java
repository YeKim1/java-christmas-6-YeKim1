package domain;

import christmas.domain.VisitDate;
import christmas.domain.Discount;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountTest {

    @DisplayName("해당하는 Event만 필드의 Map에 저장한다.")
    @Test
    void ofTest() {
        Discount discount1 = Discount.of(VisitDate.from("5"), Order.from("티본스테이크-5"));
        Discount discount2 = Discount.of(VisitDate.from("19"), Order.from("바비큐립-5"));

        assertThat(discount1).usingRecursiveComparison().isEqualTo(discount2);
    }
}
