package org.example;

import org.example.model.Restaurant;

public class RestaurantTestData {
    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Restaurant.class, "menus");

    public static final int RESTAURANT1_ID = 0;
    public static final int RESTAURANT_NOT_FOUND = 10;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID,"restaurant1", "address1", "+375291111111");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1,"restaurant2", "address2", "+375292222222");


    public static Restaurant getNew() {
        return new Restaurant(null, "newRestaurant", "addressNewRestaurant", "+375291111111");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setAddress("updatedAddress");
        return updated;
    }
}
