package org.example.service;

import org.example.model.Restaurant;

import org.example.testdata.MenuTestData;
import org.example.testdata.RestaurantTestData;
import org.example.testdata.VoteTestData;
import org.example.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.example.testdata.RestaurantTestData.*;
import static org.example.testdata.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    void get() {
        Restaurant actual = service.get(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(actual, restaurant1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_NOT_FOUND));
    }

    @Test
    void getAll() {
        List<Restaurant> actual = service.getAll();
        RESTAURANT_MATCHER.assertMatch(actual, RestaurantTestData.getAll());
    }

    @Test
    void getAllByDate() {
        List<Restaurant> actual = service.getAllActiveByDate(DATE);
        RESTAURANT_MATCHER.assertMatch(actual, restaurant1, restaurant2);
    }

    @Test
    void getWithMenusByDate() {
        Restaurant actual = service.getWithMenusByDate(RESTAURANT1_ID, DATE);
        RESTAURANT_MATCHER.assertMatch(actual, restaurant1);
        MenuTestData.MENU_WITH_DISHES_MATCHER.assertMatch(actual.getMenus(), MenuTestData.menu1);
    }

    @Test
    void getAllWithMenus() {
        List<Restaurant> actual = service.getAllWithMenusByDate(DATE);
        RESTAURANT_MATCHER.assertMatch(actual, RestaurantTestData.getAll());
        MenuTestData.MENU_WITH_DISHES_MATCHER.assertMatch(actual.get(0).getMenus(), MenuTestData.menu1);
        MenuTestData.MENU_WITH_DISHES_MATCHER.assertMatch(actual.get(1).getMenus(), MenuTestData.menu4);
    }

    @Test
    void getAllWithVotes() {
        List<Restaurant> actual = service.getAllWithVotes(DATE);
        RESTAURANT_MATCHER.assertMatch(actual, RestaurantTestData.getAll());
        VoteTestData.VOTE_WITH_RESTAURANT_MATCHER.assertMatch(actual.get(0).getVotes(), vote1, vote2);
        VoteTestData.VOTE_WITH_RESTAURANT_MATCHER.assertMatch(actual.get(1).getVotes());
    }

    @Test
    void create() {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        Restaurant created = service.create(newRestaurant);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    void duplicateNameAndAddressCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Restaurant(null, "restaurant1", "address1", "+375291111112")));
    }

    @Test
    void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test
    void delete() {
        service.delete(RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(RESTAURANT_NOT_FOUND));
    }

    @Test
    public void createWithException() {
        validateRootCause(() -> service.create(new Restaurant(null, " ", "address", "+375291111111")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Restaurant(null, "name", " ", "+375291111111")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Restaurant(null, "name", "address", "+291111111")), ConstraintViolationException.class);
    }
}

