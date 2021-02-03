package org.example.service;

import org.example.model.Menu;
import org.example.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.example.testdata.MenuTestData.*;
import static org.example.testdata.RestaurantTestData.RESTAURANT1_ID;
import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuService service;

    @Test
    void getWithDishes() {
        Menu actual = service.getWithDishes(MENU1_ID);
        MENU_WITH_DISHES_MATCHER.assertMatch(actual, menu1);
    }

    @Test
    void getWithDishesNotFound() {
        assertThrows(NotFoundException.class, () -> service.getWithDishes(MENU_NOT_FOUND));
    }

    @Test
    void getAllWithDishes() {
        List<Menu> actual = service.getAllWithDishes();
        MENU_WITH_DISHES_MATCHER.assertMatch(actual, MENUS_ALL);
    }

    @Test
    void delete() {
        service.delete(MENU1_ID);
        assertThrows(NotFoundException.class, () -> service.getWithDishes(MENU1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(MENU_NOT_FOUND));
    }

    @Test
    void update() {
        Menu updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        MENU_WITH_DISHES_MATCHER.assertMatch(service.getWithDishes(MENU1_ID), updated);
    }

    @Test
    void createWithDishes() {
        Menu created = service.createWithDishes(getNewWithDishes(), RESTAURANT1_ID);
        int newId = created.id();
        Menu newMenu = getNewWithDishes();
        newMenu.setId(newId);
        MENU_WITH_DISHES_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(service.getWithDishes(newId), newMenu);
    }

    @Test
    public void createWithException() {
        validateRootCause(() -> service.createWithDishes(getNew(), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.createWithDishes(getNewWithoutDate(), RESTAURANT1_ID), ConstraintViolationException.class);
    }
}