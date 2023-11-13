package christmas.controller;

import christmas.domain.Discount;
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

        OutputView.printEventPreviewStartMessage(visitDate.getDate());
        OutputView.printOrder(order.getNameAndCount());
        OutputView.printTotalPrice(order.calculateTotalPrice());

        Discount discount = Discount.of(visitDate, order);
        OutputView.printGiftOrNone(discount.getGiftMenuOrEmpty());
        OutputView.printDiscountOrNone(discount.getLabelAndDiscount());
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
}
