package org.example.service;

import org.example.testdata.VoteTestData;
import org.example.model.Vote;
import org.example.util.exception.IllegalVoteTimeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.example.testdata.RestaurantTestData.*;
import static org.example.testdata.VoteTestData.*;
import static org.example.testdata.UserTestData.USER1_ID;

import static org.example.testdata.VoteTestData.VOTE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VoteServiceTest extends AbstractServiceTest{

    @Autowired
    protected VoteService service;

    @Test
    void get() {
        Vote actual = service.get(DATE, USER1_ID);
        VOTE_MATCHER.assertMatch(actual, vote1);
    }

    @Test
    void getNotFound() {
        assertNull(service.get(VOTE_NOT_FOUND, USER1_ID));
    }

    @Test
    void getAllForRestaurant() {
        List<Vote> actual = service.getAllForRestaurant(DATE, RESTAURANT1_ID);
        VOTE_MATCHER.assertMatch(actual, vote1, vote2);
    }

    @Test
    void getWithRestaurant() {
        Vote actual = service.getWithRestaurant(DATE, USER1_ID);
        VOTE_WITH_RESTAURANT_MATCHER.assertMatch(actual, vote1);
    }

    @Test
    void vote() {
        service.setTimeLimitForVote(LocalTime.now().plusHours(1));
        service.vote(RESTAURANT1_ID, USER1_ID);
        Vote actual = service.getWithRestaurant(LocalDate.now(), USER1_ID);
        int id = actual.getId();
        Vote newVote = getNewWithRestAndUser();
        newVote.setId(id);
        VOTE_WITH_RESTAURANT_MATCHER.assertMatch(actual, newVote);
    }

    @Test
    void doubleWithoutExcessTimeLimitVote() {
        service.setTimeLimitForVote(LocalTime.now().plusHours(1));
        service.vote(RESTAURANT1_ID, USER1_ID);
        service.vote(RESTAURANT1_ID + 1, USER1_ID);
        Vote actual = service.getWithRestaurant(LocalDate.now(), USER1_ID);
        int id = actual.getId();
        Vote doubleVote = VoteTestData.getUpdated();
        doubleVote.setId(id);
        VOTE_WITH_RESTAURANT_MATCHER.assertMatch(actual, doubleVote);
    }

    @Test
    void doubleWithExcessTimeLimitVote() {
        service.setTimeLimitForVote(LocalTime.now().minusHours(1));
        assertThrows(IllegalVoteTimeException.class, () -> service.vote(RESTAURANT1_ID, USER1_ID));
    }

    @Test
    void delete() {
        service.delete(VOTE1_ID, USER1_ID);
        assertNull(service.get(DATE, USER1_ID));
    }
}
