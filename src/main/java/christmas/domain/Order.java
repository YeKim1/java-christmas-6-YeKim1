package christmas.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Order {
    private static final String ORDER_ERROR_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ONLY_BEVERAGES_ERROR_MESSAGE = "음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.";
    private static final String DELIMITER_BY_MENU = ",";
    private static final String DELIMITER_BY_COUNT = "-";
    private static final int NAME_POSITION = 0;
    private static final int COUNT_POSITION = 1;

    private final Map<Menu, Integer> orderedMenus;

    private Order(Map<Menu, Integer> orderedMenus) {
        this.orderedMenus = orderedMenus;
    }

    public static Order from(String inputOrder) {
        validateExistedMenu(inputOrder);
        Map<Menu, Integer> orderedMenus = parse(inputOrder);
        validateOnlyBeverages(orderedMenus);
        return new Order(orderedMenus);
    }

    public Map<String, Integer> getNameAndCount() {
        return orderedMenus.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getLabel(),
                        Map.Entry::getValue
                ));
    }

    public int calculateTotalPrice() {
        return orderedMenus.entrySet().stream()
                .mapToInt(entry ->
                        entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private static Map<Menu, Integer> parse(String inputOrder) {
        return Arrays.stream(inputOrder.split(DELIMITER_BY_MENU))
                .map(order -> order.split(DELIMITER_BY_COUNT))
                .collect(Collectors.toMap(
                        order -> Menu.getByLabel(order[NAME_POSITION]),
                        order -> Integer.parseInt(order[COUNT_POSITION])
                ));
    }

    private static void validateExistedMenu(String inputOrder) {
        if (Arrays.stream(inputOrder.split(DELIMITER_BY_MENU))
                .map(order ->
                        Menu.getByLabel(order.split(DELIMITER_BY_COUNT)[NAME_POSITION]))
                .anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private static void validateOnlyBeverages(Map<Menu, Integer> orderedMenus) {
        if (orderedMenus.keySet().stream()
                .allMatch(menu ->
                        Menu.getByCategory(MenuCategory.BEVERAGES).contains(menu))) {
            throw new IllegalArgumentException(ONLY_BEVERAGES_ERROR_MESSAGE);
        }
    }
}
