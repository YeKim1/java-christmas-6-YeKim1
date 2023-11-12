package christmas.domain;

public enum MenuCategory {
    APPETIZERS("에피타이저"),
    MAIN("메인"),
    DESSERTS("디저트"),
    BEVERAGES("음료");

    String label;

    MenuCategory(String label) {
        this.label = label;
    }
}
