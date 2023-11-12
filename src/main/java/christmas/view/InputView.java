package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final String DATE_ERROR_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final String ORDER_ERROR_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String OVER_MAX_COUNT_SUM_ERROR_MESSAGE = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.";
    private static final String DELIMITER_FOR_MENUS = ",";
    private static final String DELIMITER_FOR_COUNT = "-";
    private static final int MENU_NAME_POSITION = 0;
    private static final int MENU_COUNT_POSITION = 1;
    private static final int MIN_MENU_COUNT = 1;
    private static final int MAX_MENU_COUNT_SUM = 20;

    public static String inputDate() {
        OutputView.printInputDateMessage();
        return dateValidate(Console.readLine());
    }

    public static String inputOrder() {
        OutputView.printInputOrderMessage();
        return orderValidate(Console.readLine());
    }

    private static String dateValidate(String inputDate) {
        validateDateType(inputDate);
        validateDateRange(inputDate);
        return inputDate;
    }

    private static String orderValidate(String inputOrder) {
        validateMenuName(inputOrder);
        validateMenuCount(inputOrder);
        return inputOrder;
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

    private static void validateMenuName(String inputOrder) {
        List<String> names = Arrays.stream(inputOrder.split(DELIMITER_FOR_MENUS))
                .map(menuAndCount
                        -> menuAndCount.split(DELIMITER_FOR_COUNT)[MENU_NAME_POSITION])
                .toList();

        validateDuplicatedMenuName(names);
    }

    private static void validateMenuCount(String inputOrder) {
        List<String> counts = Arrays.stream(inputOrder.split(DELIMITER_FOR_MENUS))
                .map(menuAndCount
                        -> menuAndCount.split(DELIMITER_FOR_COUNT)[MENU_COUNT_POSITION])
                .toList();

        validateMenuCountType(counts);
        validateMenuCountRange(counts);
        validateOverMenuCountSum(counts);
    }

    private static void validateDuplicatedMenuName(List<String> names) {
        Set<String> uniqueNames = names.stream().collect(Collectors.toSet());

        if (uniqueNames.size() != names.size()) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private static void validateMenuCountType(List<String> counts) {
        try {
            counts.forEach(Integer::parseInt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private static void validateMenuCountRange(List<String> counts) {
        counts.stream().map(Integer::parseInt)
                .forEach(count -> {
                    if (count < MIN_MENU_COUNT) {
                        throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
                    }
                });
    }

    private static void validateOverMenuCountSum(List<String> counts) {
        int sum = counts.stream().mapToInt(Integer::parseInt).sum();

        if (sum > MAX_MENU_COUNT_SUM) {
            throw new IllegalArgumentException(OVER_MAX_COUNT_SUM_ERROR_MESSAGE);
        }
    }
}
