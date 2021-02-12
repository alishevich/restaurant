package org.example.web.json;

import org.example.model.*;
import org.example.testdata.RestaurantTestData;
import org.example.testdata.UserTestData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.testdata.DishTestData.*;
import static org.example.testdata.RestaurantTestData.RESTAURANT_MATCHER;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


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

    @Test
    void writeOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.user1);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.user1, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}
