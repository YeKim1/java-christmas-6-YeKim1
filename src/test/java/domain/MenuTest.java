package domain;

import christmas.domain.Menu;
import christmas.domain.MenuCategory;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {

    @DisplayName("Label을 넘기면 해당하는 Menu를 반환하는 기능 테스트")
    @Test
    void getByLabelTest() {
        Menu answer = Menu.BARBECUERIBS;

        Menu menu = Menu.getByLabel("바비큐립");

        assertThat(answer).isEqualTo(menu);
    }

    @DisplayName("Category를 넘기면 해당하는 Menu들을 반환하는 기능 테스트")
    @Test
    void getByCategoryTest() {
        List<Menu> answer = List.of(Menu.CHOCOLATECAKE, Menu.ICECREAM);

        List<Menu> menus = Menu.getByCategory(MenuCategory.DESSERTS);

        assertThat(answer).isEqualTo(menus);
    }
}
