package christmas.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Discount {

    private final Map<Event, Integer> discounts;

    private Discount(Map<Event, Integer> discounts) {
        this.discounts = discounts;
    }

    public static Discount of(VisitDate visitDate, Order order) {
        Map<Event, Integer> discounts = Arrays.stream(Event.values())
                .filter(event -> event.isMeetCondition(visitDate, order))
                .collect(Collectors.toMap(event -> event,
                        event -> event.calculateDiscount(visitDate, order)));
        return new Discount(discounts);
    }

    public int getSumOfDiscount() {
        return discounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
