package christmas.view;

import java.util.Map;

public class OutputView {
    private static final String HELLO_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String INPUT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_PREVIEW_START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String PRINT_ORDER_START_MESSAGE = "\n<주문 메뉴>";
    private static final String PRINT_MENU_MESSAGE_FORMAT = "%s %d개";
    private static final String PRINT_TOTAL_PRICE_START_MESSAGE = "\n<할인 전 총주문 금액>";
    private static final String PRINT_TOTAL_PRICE_MESSAGE_FORMAT = "%,d원";

    private static final String NONE = "없음";

    public static void printHelloMessage() {
        System.out.println(HELLO_MESSAGE);
    }

    public static void printInputDateMessage() {
        System.out.println(INPUT_DATE_MESSAGE);
    }

    public static void printInputOrderMessage() {
        System.out.println(INPUT_ORDER_MESSAGE);
    }

    public static void printEventPreviewStartMessage(int date) {
        System.out.println(String.format(EVENT_PREVIEW_START_MESSAGE, date));
    }

    public static void printOrderMenuMessage(Map<String, Integer> menus) {
        System.out.println(PRINT_ORDER_START_MESSAGE);
        printMenuMessage(menus);
    }

    public static void printTotalPriceMessage(int price) {
        System.out.println(PRINT_TOTAL_PRICE_START_MESSAGE);
        System.out.println(String.format(PRINT_TOTAL_PRICE_MESSAGE_FORMAT, price));
    }

    private static void printMenuMessage(Map<String, Integer> menus) {
        menus.forEach((key, value) ->
                System.out.println(String.format(PRINT_MENU_MESSAGE_FORMAT, key, value)));
    }
}
