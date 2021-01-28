package org.example;

import org.example.model.Menu;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.DishTestData.*;
import static org.example.RestaurantTestData.*;

public class MenuTestData {
    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Menu.class,"dishes", "restaurant");
    public static TestMatcher<Menu> MENU_WITH_DISHES_MATCHER =
            TestMatcher.usingAssertions(Menu.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("dishes", "restaurant", "dish.menu").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int MENU_ID = 0;

    public static final Menu menu1 = new Menu(MENU_ID, of(2021, Month.JANUARY, 25));
    public static final Menu menu2 = new Menu(MENU_ID + 1, of(2021, Month.JANUARY, 26));
    public static final Menu menu3 = new Menu(MENU_ID + 2, of(2021, Month.JANUARY, 27));
    public static final Menu menu4 = new Menu(MENU_ID + 3, of(2021, Month.JANUARY, 25));
    public static final Menu menu5 = new Menu(MENU_ID + 4, of(2021, Month.JANUARY, 26));
    public static final Menu menu6 = new Menu(MENU_ID + 5, of(2021, Month.JANUARY, 27));

    public static final List<Menu> MENU_REST_1 = Arrays.asList(menu1, menu2, menu3);
    public static final List<Menu> MENU_REST_2 = Arrays.asList(menu4, menu5, menu6);

    static {
        menu1.setDishes(Arrays.asList(dish1, dish2, dish3));
        menu2.setDishes(Arrays.asList(dish3, dish4, dish5));
        menu3.setDishes(Arrays.asList(dish6, dish7, dish8));
        menu4.setDishes(Arrays.asList(dish9, dish10, dish11));
        menu5.setDishes(Arrays.asList(dish12, dish13, dish14));
        menu6.setDishes(Arrays.asList(dish15, dish16, dish8));

        menu1.setRestaurant(restaurant1);
        menu2.setRestaurant(restaurant1);
        menu3.setRestaurant(restaurant1);
        menu4.setRestaurant(restaurant2);
        menu5.setRestaurant(restaurant2);
        menu6.setRestaurant(restaurant2);
    }

    public static Menu getNew() {
        return new Menu(null, of(2021, Month.JANUARY, 28));
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID, menu1.getDate().plusDays(5));
    }
}
