package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Benefit;
import christmas.domain.VisitDate;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void startChristmas() {
        OutputView.printWelcomeMessage();

        VisitDate visitDate = createDate();
        Order order = createOrder();

        printEventPreviewStartMessage(visitDate);
        printOrderDetail(order);

        Benefit benefit = Benefit.of(visitDate, order);

        printBenefitDetail(benefit);
        printExpectedDiscountedPrice(
                order.calculateTotalPrice() - benefit.getSumOfDiscountPrice());
        printBadge(Badge.findByBenefitPrice(benefit.getSumOfBenefitPrice()));
    }

    private VisitDate createDate() {
        while (true) {
            try {
                return VisitDate.from(InputView.inputDate());
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_MESSAGE_PREFIX + e.getMessage());
            }
        }
    }

    private Order createOrder() {
        while (true) {
            try {
                return Order.from(InputView.inputOrder());
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_MESSAGE_PREFIX + e.getMessage());
            }
        }
    }

    private void printEventPreviewStartMessage(VisitDate visitDate) {
        OutputView.printEventPreviewStartMessage(visitDate.getDate());
    }

    private void printOrderDetail(Order order) {
        OutputView.printOrder(order.getNameAndCount());
        OutputView.printTotalPrice(order.calculateTotalPrice());
    }

    private void printBenefitDetail(Benefit benefit) {
        OutputView.printGiftOrNone(benefit.getGiftMenuOrEmpty());
        OutputView.printDiscountOrNone(benefit.getLabelAndBenefitPrice());
        OutputView.printTotalBenefitPrice(benefit.getSumOfBenefitPrice());
    }

    private void printExpectedDiscountedPrice(int price) {
        OutputView.printExpectedDiscountedPrice(price);
    }

    private void printBadge(String badgeLabel) {
        OutputView.printBadge(badgeLabel);
    }
}
