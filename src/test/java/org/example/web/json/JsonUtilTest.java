package org.example.web.json;

import org.example.model.*;
import org.example.testdata.RestaurantTestData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.testdata.DishTestData.*;
import static org.example.testdata.RestaurantTestData.RESTAURANT_MATCHER;



public class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(RestaurantTestData.getNew());
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurant, RestaurantTestData.getNew());
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(getNew());
        System.out.println(json);
        List<Dish> dishes = JsonUtil.readValues(json, Dish.class);
        DISH_MATCHER.assertMatch(dishes, getNew());
    }
}
