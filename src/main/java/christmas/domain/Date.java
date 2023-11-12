package christmas.domain;

public class Date {
    private static final String DATE_ERROR_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MIN = 1;
    private static final int MAX = 31;

    private final int date;

    private Date(int date) {
        this.date = date;
    }

    public static Date from(String inputDate) {
        validate(inputDate);
        return new Date(Integer.parseInt(inputDate));
    }

    private static void validate(String inputDate) {
        validateType(inputDate);
        validateRange(inputDate);
    }

    private static void validateType(String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_ERROR_MESSAGE);
        }
    }

    private static void validateRange(String inputDate) {
        int date = Integer.parseInt(inputDate);

        if (date < MIN || date > MAX) {
            throw new IllegalArgumentException(DATE_ERROR_MESSAGE);
        }
    }
}
