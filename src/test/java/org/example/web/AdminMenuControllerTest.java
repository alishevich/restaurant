package org.example.web;

import org.example.model.Menu;
import org.example.service.MenuService;
import org.example.util.exception.NotFoundException;
import org.example.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.example.TestUtil.readFromJson;
import static org.example.testdata.MenuTestData.*;
import static org.example.testdata.RestaurantTestData.RESTAURANT1_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminMenuControllerTest extends AbstractControllerTest{
    private static final String REST_URL = AdminMenuController.REST_URL + '/' ;

    @Autowired
    private MenuService service;

    @Test
    void getWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_WITH_DISHES_MATCHER.contentJson(menu1));
    }

    @Test
    void getAllWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_WITH_DISHES_MATCHER.contentJson(MENUS_ALL));
    }

    @Test
    void createWithLocation() throws Exception {
        Menu newMenu = getNewWithDishes();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu))
                .param("restaurantId", String.valueOf(RESTAURANT1_ID)));

        Menu created = readFromJson(action, Menu.class);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_WITH_DISHES_MATCHER.assertMatch(created, newMenu);
        MENU_WITH_DISHES_MATCHER.assertMatch(service.getWithDishes(newId), newMenu);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.getWithDishes(MENU1_ID));
    }

    @Test
    void update() throws Exception {
        Menu updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .param("restaurantId", String.valueOf(RESTAURANT1_ID)))
                .andExpect(status().isNoContent());

        MENU_WITH_DISHES_MATCHER.assertMatch(service.getWithDishes(MENU1_ID), updated);
    }
}