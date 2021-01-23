package org.example.web.user;

import org.example.model.User;
import org.springframework.stereotype.Controller;

import static org.example.web.SecurityUtil.authUserId;

@Controller
public class ProfileController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}
