package christmas.domain;

public enum Badge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");

    private static final int STAR_CONDITION = 5000;
    private static final int TREE_CONDITION = 10000;
    private static final int SANTA_CONDITION = 20000;

    private final String label;

    Badge(String label) {
        this.label = label;
    }

    public static String findByBenefitPrice(int benefitPrice) {
        if (benefitPrice >= SANTA_CONDITION) {
            return SANTA.label;
        }

        if (benefitPrice >= TREE_CONDITION) {
            return TREE.label;
        }

        if (benefitPrice >= STAR_CONDITION) {
            return STAR.label;
        }

        return NONE.label;
    }
}
