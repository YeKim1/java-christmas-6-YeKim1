package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Event {
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인", EventType.DISCOUNT,
            (date, order) -> date.isLessThan(26)) {
        @Override
        public int calculateBenefit(VisitDate visitDate, Order order) {
            return CHRISTMAS_BASE_DISCOUNT +
                    CHRISTMAS_ADDITION_DISCOUNT * visitDate.subtractDate(CHRISTMAS_FIRST_DAY);
        }
    },
    WEEKEND_DISCOUNT("주말 할인", EventType.DISCOUNT,
            (date, order) -> date.isWeekend()) {
        @Override
        public int calculateBenefit(VisitDate visitDate, Order order) {
            return Event.calculateByCategory(order, WEEKEND_DISCOUNT_CATEGORY, WEEK_DISCOUNT_PRICE);
        }
    },
    WEEKDAY_DISCOUNT("평일 할인", EventType.DISCOUNT,
            (date, order) -> !date.isWeekend()) {
        @Override
        public int calculateBenefit(VisitDate visitDate, Order order) {
            return Event.calculateByCategory(order, WEEKDAY_DISCOUNT_CATEGORY, WEEK_DISCOUNT_PRICE);
        }
    },
    SPECIAL_DISCOUNT("특별 할인", EventType.DISCOUNT,
            (date, order) -> date.isContainedIn(List.of(3, 10, 17, 24, 25, 31))) {
        @Override
        public int calculateBenefit(VisitDate visitDate, Order order) {
            return SPECIAL_DISCOUNT_PRICE;
        }
    },
    GIFTS_EVENT("증정 이벤트", EventType.GIFT,
            (date, order) -> order.calculateTotalPrice() >= 120000) {
        @Override
        public int calculateBenefit(VisitDate visitDate, Order order) {
            return GIFT.getPrice();
        }
    };

    private static final int CHRISTMAS_BASE_DISCOUNT = 1000;
    private static final int CHRISTMAS_ADDITION_DISCOUNT = 100;
    private static final int CHRISTMAS_FIRST_DAY = 1;
    private static final MenuCategory WEEKEND_DISCOUNT_CATEGORY = MenuCategory.MAIN;
    private static final MenuCategory WEEKDAY_DISCOUNT_CATEGORY = MenuCategory.DESSERTS;
    private static final int WEEK_DISCOUNT_PRICE = 2023;
    private static final int SPECIAL_DISCOUNT_PRICE = 1000;
    private static final Menu GIFT = Menu.CHAMPAGNE;
    private static final int GIFT_COUNT = 1;

    private final String label;
    private final EventType eventType;
    private final BiPredicate<VisitDate, Order> condition;

    Event(String label, EventType eventType, BiPredicate<VisitDate, Order> condition) {
        this.label = label;
        this.eventType = eventType;
        this.condition = condition;
    }

    public String getLabel() {
        return label;
    }

    public static Map<String, Integer> getGiftMenu() {
        return Map.of(GIFT.getLabel(), GIFT_COUNT);
    }

    public static List<Event> findByType(EventType type) {
        return Arrays.stream(Event.values())
                .filter(event -> event.eventType.equals(type))
                .collect(Collectors.toList());
    }

    public boolean isMeetCondition(VisitDate visitDate, Order order) {
        return this.condition.test(visitDate, order);
    }

    public abstract int calculateBenefit(VisitDate visitDate, Order order);

    private static int calculateByCategory(Order order, MenuCategory category, int discountPrice) {
        Map<Menu, Integer> orderedMenuOfCategory = order.findOrderedMenusOfCategory(category);
        if (orderedMenuOfCategory.isEmpty()) {
            return 0;
        }
        int sumOfMenus = orderedMenuOfCategory.values().stream()
                .mapToInt(Integer::intValue).sum();
        return discountPrice * sumOfMenus;
    }
}
