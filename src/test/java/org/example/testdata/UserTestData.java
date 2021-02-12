package org.example.testdata;

import org.example.TestMatcher;
import org.example.model.Role;
import org.example.model.User;

import java.util.Collections;
import java.util.Date;

public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class, "password", "registered", "votes");

    public static final int ADMIN_ID = 0;
    public static final int USER1_ID = ADMIN_ID + 1;
    public static final int USER_NOT_FOUND = 10;


    public static final User user1 = new User(USER1_ID, "User1", "user1@yandex.ru", "password1", Role.USER);
    public static final User user2 = new User(USER1_ID + 1, "User2", "user2@yandex.ru", "password2", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user1);
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        return updated;
    }
}
