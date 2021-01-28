package org.example.service;

import org.example.model.Role;
import org.example.model.User;
import org.example.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.example.testdata.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends AbstractServiceTest{

    @Autowired
    protected UserService service;

    @Test
    void create() {
        User created = service.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user1@yandex.ru", "newPass",
                        false, new Date(), Collections.singleton(Role.USER))));
    }

    @Test
    void delete() {
        service.delete(USER1_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER1_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(USER_NOT_FOUND));
    }

    @Test
    void get() {
        User user = service.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(USER_NOT_FOUND));
    }

    @Test
    void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER1_ID), getUpdated());
    }

    @Test
    void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, admin, user1, user2);
    }

    @Test
    void enable() {
        service.enable(USER1_ID, false);
        assertFalse(service.get(USER1_ID).isEnabled());
        service.enable(USER1_ID, true);
        assertTrue(service.get(USER1_ID).isEnabled());
    }
}
