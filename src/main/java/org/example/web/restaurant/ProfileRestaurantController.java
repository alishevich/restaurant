package org.example.web.restaurant;

import org.example.model.Restaurant;
import org.example.service.RestaurantService;
import org.example.to.RestaurantTo;
import org.example.util.RestaurantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantController {
    static final String REST_URL = "/rest/profile/restaurants";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @GetMapping("/with-menus")
    public List<Restaurant> getAllWithMenus(@RequestParam @Nullable LocalDate date) {
        log.info("getAllWithMenusByDate {}", date);
        return service.getAllWithMenusByDate(date);
    }

    @GetMapping("/with-votes")
    public List<RestaurantTo> getAllWithCountOfVotes(@RequestParam @Nullable LocalDate date) {
        log.info("getAllWithCountOfVotes {}", date);
        return RestaurantUtil.getTos(service.getAllWithVotes(date));
    }
}
