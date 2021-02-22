package org.example.testdata;

import org.example.TestMatcher;
import org.example.model.Dish;

import java.util.Arrays;
import java.util.List;

public class DishTestData {
    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Dish.class,"menu");

    public static final int DISH_ID = 0;

    public static final Dish dish1 = new Dish(DISH_ID, "dish1", 10);
    public static final Dish dish2 = new Dish(DISH_ID + 1, "dish2", 11);
    public static final Dish dish3 = new Dish(DISH_ID + 2, "dish3", 12);

    public static final Dish dish4 = new Dish(DISH_ID + 3, "dish4", 20);
    public static final Dish dish5 = new Dish(DISH_ID + 4, "dish5", 21);
    public static final Dish dish6 = new Dish(DISH_ID + 5, "dish6", 22);

    public static final Dish dish7 = new Dish(DISH_ID + 6, "dish7", 30);
    public static final Dish dish8 = new Dish(DISH_ID + 7, "dish8", 31);
    public static final Dish dish9 = new Dish(DISH_ID + 8, "dish9", 32);

    public static final Dish dish10 = new Dish(DISH_ID + 9, "dish10", 40);
    public static final Dish dish11 = new Dish(DISH_ID + 10, "dish11", 41);
    public static final Dish dish12 = new Dish(DISH_ID + 11, "dish12", 42);

    public static final Dish dish13 = new Dish(DISH_ID + 12, "dish13", 50);
    public static final Dish dish14 = new Dish(DISH_ID + 13, "dish14", 51);
    public static final Dish dish15 = new Dish(DISH_ID + 14, "dish15", 52);

    public static final Dish dish16 = new Dish(DISH_ID + 15, "dish16", 60);
    public static final Dish dish17 = new Dish(DISH_ID + 16, "dish17", 61);
    public static final Dish dish18 = new Dish(DISH_ID + 17, "dish18", 62);

    public static List<Dish> getNew() {
        return Arrays.asList(new Dish(null, "newDish1", 1),
                             new Dish(null, "newDish2", 2));
    }
}
