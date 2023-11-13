package christmas.view;

import java.util.Map;

public class OutputView {
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String INPUT_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_PREVIEW_START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "\n<주문 메뉴>";
    private static final String TOTAL_PRICE_HEADER = "\n<할인 전 총주문 금액>";
    private static final String GIFT_MENU_HEADER = "\n<증정 메뉴>";
    private static final String DISCOUNT_HEADER = "\n<혜택 내역>";
    private static final String TOTAL_DISCOUNT_PRICE_HEADER = "\n<총혜택 금액>";
    private static final String EXPECTED_DISCOUNTED_PRICE_HEADER = "\n<할인 후 예상 결제 금액>";
    private static final String MENU_FORMAT = "%s %d개";
    private static final String PRICE_FORMAT = "%,d원";
    private static final String DISCOUNT_FORMAT = "%s: -%,d원";
    private static final String NONE = "없음";

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printInputVisitDateMessage() {
        System.out.println(INPUT_VISIT_DATE_MESSAGE);
    }

    public static void printInputOrderMessage() {
        System.out.println(INPUT_ORDER_MESSAGE);
    }

    public static void printEventPreviewStartMessage(int date) {
        System.out.println(String.format(EVENT_PREVIEW_START_MESSAGE, date));
    }

    public static void printOrder(Map<String, Integer> menus) {
        System.out.println(ORDER_MENU_HEADER);
        printMenus(menus);
    }

    public static void printTotalPrice(int totalPrice) {
        System.out.println(TOTAL_PRICE_HEADER);
        System.out.println(String.format(PRICE_FORMAT, totalPrice));
    }

    public static void printGiftOrNone(Map<String, Integer> gift) {
        System.out.println(GIFT_MENU_HEADER);
        gift.entrySet().stream().findAny().ifPresentOrElse(
                entry -> printMenus(gift),
                OutputView::printNone
        );
    }

    public static void printDiscountOrNone(Map<String, Integer> discounts) {
        System.out.println(DISCOUNT_HEADER);
        discounts.entrySet().stream().findAny().ifPresentOrElse(
                entry -> printDiscounts(discounts),
                OutputView::printNone
        );
    }

    public static void printNone() {
        System.out.println(NONE);
    }

    private static void printMenus(Map<String, Integer> menus) {
        menus.forEach((key, value) ->
                System.out.println(String.format(MENU_FORMAT, key, value)));
    }

    private static void printDiscounts(Map<String, Integer> discounts) {
        discounts.entrySet().forEach(entry
                -> System.out.println(String.format(DISCOUNT_FORMAT, entry.getKey(), entry.getValue())));
    }
}
