package org.example.web;

import org.example.model.Vote;
import org.example.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteController {
    static final String REST_URL = "/rest/profile/votes";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @GetMapping("/toDate")
    public Vote getWithRestaurant(@RequestParam LocalDate date, @RequestParam int userId) {
        log.info("get vote by date {} for user {}", date, userId);
        return service.getWithRestaurant(date, userId);
    }

    @PatchMapping("/")
    public void vote(@RequestParam int restaurantId, @RequestParam int userId) {
        log.info("vote user {} by  restaurant {}", userId, restaurantId);
        service.vote(restaurantId, userId);
    }
}
