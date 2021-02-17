package org.example.testdata;

import org.example.TestMatcher;
import org.example.model.Menu;
import org.example.model.Restaurant;
import org.example.to.RestaurantTo;
import org.example.util.RestaurantUtil;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.testdata.MenuTestData.*;
import static org.example.testdata.VoteTestData.*;

public class RestaurantTestData {
    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Restaurant.class, "menus", "votes");
    public static final TestMatcher<RestaurantTo> RESTAURANT_TO_MATCHER = TestMatcher.usingIgnoringFieldsComparator(RestaurantTo.class);

    public static final int RESTAURANT1_ID = 0;
    public static final int RESTAURANT_NOT_FOUND = 10;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID,"restaurant1", "address1", "+375291111111");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1,"restaurant2", "address2", "+375292222222");

    static {
        restaurant1.setMenus(Arrays.asList(menu1, menu2, menu3));
        restaurant2.setMenus(Arrays.asList(menu4, menu5, menu6));

        restaurant1.setVotes(Arrays.asList(vote1, vote2, vote3));
        restaurant2.setVotes(Arrays.asList(vote4));

    }


    public static Restaurant getNew() {
        return new Restaurant(null, "newRestaurant", "addressNewRestaurant", "+375291111111");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setAddress("updatedAddress");
        return updated;
    }

    public static List<Restaurant> getAll() {
        return Arrays.asList(restaurant1, restaurant2);
    }

    public static List<RestaurantTo> getAllWithVotes() {
        RestaurantTo restaurantTo1 = RestaurantUtil.createTo(restaurant1);
        RestaurantTo restaurantTo2 = RestaurantUtil.createTo(restaurant2);
        restaurantTo1.setVotesCounter(2);
        restaurantTo2.setVotesCounter(0);

        return Arrays.asList(restaurantTo1, restaurantTo2);
    }
}
