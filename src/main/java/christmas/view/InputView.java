package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String DATE_ERROR_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    public static String inputDate() {
        OutputView.printInputDateMessage();
        return dateValidate(Console.readLine());
    }

    private static String dateValidate(String inputDate) {
        validateDateType(inputDate);
        validateDateRange(inputDate);
        return inputDate;
    }

    private static void validateDateType(String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_ERROR_MESSAGE);
        }
    }

    private static void validateDateRange(String inputDate) {
        int date = Integer.parseInt(inputDate);

        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(DATE_ERROR_MESSAGE);
        }
    }
}
