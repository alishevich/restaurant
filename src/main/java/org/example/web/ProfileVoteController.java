package org.example.web;

import org.example.model.Vote;
import org.example.service.VoteService;
import org.example.to.VoteTo;
import org.example.util.ValidationUtil;
import org.example.util.VoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

import static org.example.util.ValidationUtil.checkNotFound;
import static org.example.util.VoteUtil.asTo;
import static org.example.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteController {
    static final String REST_URL = "/rest/profile/votes";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @GetMapping("/with-restaurant")
    public VoteTo get(@RequestParam @Nullable LocalDate date) {
        int userId = authUserId();
        log.info("get vote by date {} for user {}", date, userId);
        return asTo(checkNotFound(service.get(date, userId),
                "date " + date + " for user " + userId));
    }

    @PatchMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@RequestParam int restaurantId) {
        int userId = authUserId();
        log.info("vote user {} by  restaurant {}", userId, restaurantId);
        service.vote(restaurantId, userId);
    }
}
