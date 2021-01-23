package org.example;

import org.example.model.Restaurant;

public class RestaurantTestData {
  //  public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingIgnoringFieldsComparator();

    public static final int RESTAURANT_ID = 0;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID,"restaurant1", "address1", "+375291111111");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1,"restaurant2", "address2", "+375292222222");


    public static Restaurant getNew() {
        return new Restaurant(null, "newRestaurant", "addressNewRestaurant", "+375291111111");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setAddress("updatedAddress");
        return updated;
    }
}
