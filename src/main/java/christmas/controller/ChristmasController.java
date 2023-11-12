package christmas.controller;

import christmas.domain.Date;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void startChristmas() {
        OutputView.printHelloMessage();
        Date date = createDate();
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
}
