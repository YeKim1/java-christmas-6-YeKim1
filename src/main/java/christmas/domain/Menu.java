package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    MUSHROOMSOUP("양송이수프", MenuCategory.APPETIZERS, 6000),
    TAPAS("타파스", MenuCategory.APPETIZERS, 5500),
    CAESARSALAD("시저샐러드", MenuCategory.APPETIZERS, 8000),

    TBONESTEAK("티본스테이크", MenuCategory.MAIN, 55000),
    BARBECUERIBS("바비큐립", MenuCategory.MAIN, 54000),
    SEAFOODPASTA("해산물파스타", MenuCategory.MAIN, 35000),
    CHRISTMASPASTA("크리스마스파스타", MenuCategory.MAIN, 25000),

    CHOCOLATECAKE("초코케이크", MenuCategory.DESSERTS, 15000),
    ICECREAM("아이스크림", MenuCategory.DESSERTS, 5000),

    ZEROCOLA("제로콜라", MenuCategory.BEVERAGES, 3000),
    REDWINE("레드와인", MenuCategory.BEVERAGES, 60000),
    CHAMPAGNE("샴페인", MenuCategory.BEVERAGES, 25000);

    private final String label;
    private final MenuCategory category;
    private final int price;

    Menu(String label, MenuCategory category, int price) {
        this.label = label;
        this.category = category;
        this.price = price;
    }

    public final String getLabel() {
        return label;
    }

    public final int getPrice() {
        return price;
    }

    public static Menu findByLabel(String label) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.label.equals(label))
                .findAny()
                .orElse(null);
    }

    public static List<Menu> findByCategory(MenuCategory menuCategory) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.category.equals(menuCategory))
                .collect(Collectors.toList());
    }
}
