package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void startChristmas() {
        OutputView.printHelloMessage();
        Date date = createDate();
        Order order = createOrder();

        OutputView.printOrderMessage(order.getNameAndCount());
    }

    private Date createDate() {
        while (true) {
            try {
                return Date.from(InputView.inputDate());
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
