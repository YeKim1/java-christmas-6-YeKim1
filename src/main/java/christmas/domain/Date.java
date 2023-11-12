package christmas.domain;

public class Date {

    private final int date;

    private Date(int date) {
        this.date = date;
    }

    public static Date from(String inputDate) {
        return new Date(Integer.parseInt(inputDate));
    }
}
