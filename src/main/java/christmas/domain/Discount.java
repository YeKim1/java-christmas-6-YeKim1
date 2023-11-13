package christmas.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Discount {

    private final Map<Event, Integer> discounts;

    private Discount(Map<Event, Integer> discounts) {
        this.discounts = discounts;
    }

    public static Discount of(Date date, Order order) {
        Map<Event, Integer> discounts = Arrays.stream(Event.values())
                .filter(event -> event.isMeetCondition(date, order))
                .collect(Collectors.toMap(event -> event,
                        event -> event.calculateDiscount(date, order)));
        return new Discount(discounts);
    }
}
