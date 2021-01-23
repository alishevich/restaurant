package org.example.web;


import org.example.model.Menu;
import org.example.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

import static org.example.util.ValidationUtil.assureIdConsistent;
import static org.example.util.ValidationUtil.checkNew;

@Controller
public class MenuController {
    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    public Menu get(int id, int restaurantId) {
        log.info("get menu {} for restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete menu {} for restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public List<Menu> getAll(int restaurantId) {
        log.info("getAll for restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    public Menu create(Menu menu, int restaurantId) {
        checkNew(menu);
        log.info("create {} for restaurant {}", menu, restaurantId);
        return service.create(menu, restaurantId);
    }

    public void update(Menu menu, int id, int restaurantId) {
        assureIdConsistent(menu, id);
        log.info("update {} for restaurant {}", menu, restaurantId);
        service.update(menu, restaurantId);
    }

    public List<Menu> getBetween(LocalDate startDate, LocalDate endDate, int restaurantId) {
        log.info("getBetween dates({} - {}) for restaurant {}", startDate, endDate, restaurantId);
        return service.getBetweenInclusive(startDate, endDate, restaurantId);
    }
}
