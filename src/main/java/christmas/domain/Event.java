package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public enum Event {
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인", (date, order) -> date.isLessThan(26)) {
        @Override
        public int calculateDiscount(Date date, Order order) {
            return 1000 + 100 * date.subtractDate(1);
        }
    },
    WEEKEND_DISCOUNT("주말 할인", (date, order) -> date.isWeekend()) {
        @Override
        public int calculateDiscount(Date date, Order order) {
            return Event.calculateByCategory(order, MenuCategory.MAIN, 2023);
        }
    },
    WEEKDAY_DISCOUNT("평일 할인", (date, order) -> !date.isWeekend()) {
        @Override
        public int calculateDiscount(Date date, Order order) {
            return Event.calculateByCategory(order, MenuCategory.DESSERTS, 2023);
        }
    },
    SPECIAL_DISCOUNT("특별 할인", (date, order) ->
            date.isContainedIn(List.of(3, 10, 17, 24, 25, 31))) {
        @Override
        public int calculateDiscount(Date date, Order order) {
            return 1000;
        }
    },
    GIFTS_EVENT("증정 이벤트", (date, order) -> order.calculateTotalPrice() >= 120000) {
        @Override
        public int calculateDiscount(Date date, Order order) {
            return Menu.CHAMPAGNE.getPrice();
        }
    };

    private final String label;
    private final BiPredicate<Date, Order> condition;

    Event(String label, BiPredicate<Date, Order> condition) {
        this.label = label;
        this.condition = condition;
    }

    public boolean isMeetCondition(Date date, Order order) {
        return this.condition.test(date, order);
    }

    public abstract int calculateDiscount(Date date, Order order);

    private static int calculateByCategory(Order order, MenuCategory category, int discountPrice) {
        Map<Menu, Integer> orderedMenuOfCategory = order.getOrderedMenusOfCategory(category);
        if (orderedMenuOfCategory.isEmpty()) {
            return 0;
        }
        int sumOfMenus = orderedMenuOfCategory.values().stream()
                .mapToInt(Integer::intValue).sum();
        return discountPrice * sumOfMenus;
    }
}
