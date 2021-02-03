package org.example.web;

import org.example.model.Menu;
import org.example.service.MenuService;
import org.example.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuController {
    static final String REST_URL = "/rest/admin/menus";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    @GetMapping("/{id}")
    public Menu getWithDishes(@PathVariable int id) {
        log.info("getWithDishes {} ", id);
        return service.getWithDishes(id);
    }

    @GetMapping
    public List<Menu> getAllWithDishes() {
        log.info("getAllWithDishes");
        return service.getAllWithDishes();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @RequestBody int restaurantId) {
        ValidationUtil.checkNew(menu);
        log.info("create {}", menu);
        Menu created = service.createWithDishes(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Menu menu, @RequestBody int restaurantId) {
        log.info("update {} for restaurant {}", menu, restaurantId);
        service.update(menu, restaurantId);
    }
}
