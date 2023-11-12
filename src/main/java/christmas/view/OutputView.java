package christmas.view;

public class OutputView {
    private static final String HELLO_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String INPUT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public static void printHelloMessage() {
        System.out.println(HELLO_MESSAGE);
    }

    public static void printInputDateMessage() {
        System.out.println(INPUT_DATE_MESSAGE);
    }
}
