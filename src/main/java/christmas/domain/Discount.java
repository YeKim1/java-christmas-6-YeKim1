package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Discount {
    private static final int MIN_TOTAL_PRICE_FOR_DISCOUNT = 10000;

    private final Map<Event, Integer> discounts;

    private Discount(Map<Event, Integer> discounts) {
        this.discounts = discounts;
    }

    public static Discount of(VisitDate visitDate, Order order) {
        Map<Event, Integer> discounts = new HashMap<>();

        if (order.calculateTotalPrice() >= MIN_TOTAL_PRICE_FOR_DISCOUNT) {
            Arrays.stream(Event.values())
                    .filter(event -> event.isMeetCondition(visitDate, order))
                    .forEach(event -> discounts.put(event, event.calculateDiscount(visitDate, order)));
        }

        return new Discount(discounts);
    }

    public int getSumOfDiscountPrice() {
        return discounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }


    public Map<String, Integer> getLabelAndDiscount() {
        return discounts.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getLabel(),
                        Map.Entry::getValue
                ));
    }

    public Map<String, Integer> getGiftMenuOrEmpty() {
        if (discounts.containsKey(Event.GIFTS_EVENT)) {
            return Event.getGiftMenu();
        }
        return Collections.EMPTY_MAP;
    }

    public boolean containsEvent(Event event) {
        return discounts.containsKey(event);
    }
}
