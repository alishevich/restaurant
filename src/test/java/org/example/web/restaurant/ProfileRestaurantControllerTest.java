package org.example.web.restaurant;

import org.example.model.Restaurant;
import org.example.testdata.RestaurantTestData;
import org.example.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.example.TestUtil.readListFromJson;
import static org.example.TestUtil.userHttpBasic;
import static org.example.testdata.MenuTestData.*;
import static org.example.testdata.RestaurantTestData.*;
import static org.example.testdata.UserTestData.admin;
import static org.example.testdata.UserTestData.user1;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestaurantControllerTest extends AbstractControllerTest {
    private final String REST_URL = ProfileRestaurantController.REST_URL + '/';

    @Test
    void getAllWithMenus() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL + "/with-menus")
                .param("date", "2021-01-25")
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(getAll()));

        List<Restaurant> restaurants = readListFromJson(action, Restaurant.class);
        MENU_WITH_DISHES_MATCHER.assertMatch(restaurants.get(0).getMenus(), menu1);
        MENU_WITH_DISHES_MATCHER.assertMatch(restaurants.get(1).getMenus(), menu4);
    }

    @Test
    void getAllWithCountOfVotes() throws Exception {
       perform(MockMvcRequestBuilders.get(REST_URL + "with-votes")
                .param("date", "2021-01-25")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(RestaurantTestData.getAllWithVotes()));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/with-menus"))
                .andExpect(status().isUnauthorized());
    }



}