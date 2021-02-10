package org.example.web.user;

import org.example.model.User;
import org.example.service.UserService;
import org.example.to.UserTo;
import org.example.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.example.util.ValidationUtil.assureIdConsistent;
import static org.example.util.ValidationUtil.checkNew;

public class AbstractUserController {
       protected final Logger log = LoggerFactory.getLogger(getClass());

       @Autowired
       protected  UserService service;

       public List<User> getAll() {
              log.info("getAll");
              return service.getAll();
       }

       public User get(int id) {
              log.info("get {}", id);
              return service.get(id);
       }

       public void create(UserTo userTo) {
              log.info("create from to {}", userTo);
              checkNew(userTo);
              create(UserUtil.createNewFromTo(userTo));
       }

       public User create(User user) {
              log.info("create {}", user);
              checkNew(user);
              return service.create(user);
       }

       public void delete(int id) {
              log.info("delete {}", id);
              service.delete(id);
       }

       public void update(User user, int id) {
              log.info("update {} with id={}", user, id);
              assureIdConsistent(user, id);
              service.update(user);
       }

       public void update(UserTo userTo, int id) {
              log.info("update {} with id={}", userTo, id);
              assureIdConsistent(userTo, id);
              service.update(userTo);
       }

       public User getByMail(String email) {
              log.info("getByEmail {}", email);
              return service.getByEmail(email);
       }

       public void enable(int id, boolean enabled) {
              log.info(enabled ? "enable {}" : "disable {}", id);
              service.enable(id, enabled);
       }

}
