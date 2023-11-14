package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Benefit {
    private static final int MIN_TOTAL_PRICE_FOR_DISCOUNT = 10000;

    private final Map<Event, Integer> benefits;

    private Benefit(Map<Event, Integer> benefits) {
        this.benefits = benefits;
    }

    public static Benefit of(VisitDate visitDate, Order order) {
        Map<Event, Integer> benefits = new HashMap<>();

        if (order.calculateTotalPrice() >= MIN_TOTAL_PRICE_FOR_DISCOUNT) {
            Arrays.stream(Event.values())
                    .filter(event -> event.isMeetCondition(visitDate, order))
                    .forEach(event -> benefits.put(event, event.calculateBenefit(visitDate, order)));
        }

        return new Benefit(benefits);
    }

    public int getSumOfBenefitPrice() {
        return benefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getSumOfDiscountPrice() {
        return benefits.entrySet().stream()
                .filter(benefit ->
                        Event.getByType(EventType.DISCOUNT).contains(benefit.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public Map<String, Integer> getLabelAndBenefitPrice() {
        return benefits.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getLabel(),
                        Map.Entry::getValue
                ));
    }

    public Map<String, Integer> getGiftMenuOrEmpty() {
        if (benefits.containsKey(Event.GIFTS_EVENT)) {
            return Event.getGiftMenu();
        }
        return Collections.EMPTY_MAP;
    }
}
