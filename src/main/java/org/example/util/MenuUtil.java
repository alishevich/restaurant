package org.example.util;

import org.example.model.Dish;
import org.example.model.Menu;

import java.util.List;
import java.util.stream.Collectors;

public class MenuUtil {

    public static void addDishes(Menu menu, List<Dish> dishes) {
        menu.setDishes(dishes.stream()
                .peek(dish -> dish.setMenu(menu))
                .collect(Collectors.toList()));
    }
}
