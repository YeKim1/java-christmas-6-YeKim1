package domain;

import christmas.domain.VisitDate;
import christmas.domain.Benefit;
import christmas.domain.Order;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitTest {

    @DisplayName("총혜택 금액 계산 테스트")
    @Test
    void getSumOfBenefitTest() {
        Benefit benefit = Benefit.of(VisitDate.from("1"), Order.from("티본스테이크-3,바비큐립-5,제로콜라-1"));

        assertThat(benefit.getSumOfBenefitPrice()).isEqualTo(42184);
    }

    @DisplayName("총할인 금액 계산 테스트")
    @Test
    void getSumOfDiscountTest() {
        Benefit benefit = Benefit.of(VisitDate.from("1"), Order.from("티본스테이크-3,바비큐립-5,제로콜라-1"));

        assertThat(benefit.getSumOfDiscountPrice()).isEqualTo(17184);
    }

    @DisplayName("혜택이 없을 경우, 총혜택 금액은 0이어야 한다.")
    @Test
    void getSumofDiscountIfNonDiscount() {
        Benefit benefit = Benefit.of(VisitDate.from("2"), Order.from("타파스-1"));

        assertThat(benefit.getSumOfDiscountPrice()).isEqualTo(0);
    }

    @DisplayName("증정 메뉴가 없을 경우, Empty Map을 반환한다.")
    @Test
    void getGiftMenuOrEmptyTest() {
        Benefit benefit = Benefit.of(VisitDate.from("2"), Order.from("초코케이크-1"));

        assertThat(benefit.getGiftMenuOrEmpty()).isEqualTo(Collections.EMPTY_MAP);
    }
}
