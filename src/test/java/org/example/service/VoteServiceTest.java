package org.example.service;

import org.example.testdata.VoteTestData;
import org.example.model.Vote;
import org.example.util.exception.IllegalVoteTimeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.example.testdata.RestaurantTestData.*;
import static org.example.testdata.VoteTestData.*;
import static org.example.testdata.UserTestData.USER1_ID;

import static org.example.testdata.VoteTestData.VOTE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;

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
    void vote() {
        service.setDeadline(LocalTime.now().plusHours(1));
        service.vote(RESTAURANT1_ID, USER1_ID);
        Vote actual = service.get(LocalDate.now(), USER1_ID);
        int id = actual.getId();
        Vote newVote = getNewWithRestAndUser();
        newVote.setId(id);
        VOTE_MATCHER.assertMatch(actual, newVote);
        assertEquals(actual.getRestaurant().getId(), RESTAURANT1_ID);
        service.setDeadline(LocalTime.of(11, 0));
    }

    @Test
    void doubleWithoutExcessDeadline() {
        service.setDeadline(LocalTime.now().plusHours(1));
        service.vote(RESTAURANT1_ID, USER1_ID);
        service.vote(RESTAURANT1_ID + 1, USER1_ID);
        Vote actual = service.get(LocalDate.now(), USER1_ID);
        int id = actual.getId();
        Vote doubleVote = VoteTestData.getUpdated();
        doubleVote.setId(id);
        VOTE_MATCHER.assertMatch(actual, doubleVote);
        assertEquals(actual.getRestaurant().getId(), RESTAURANT1_ID + 1);
    }

    @Test
    void doubleWithExcessDeadline() {
        service.setDeadline(LocalTime.now().minusHours(1));
        assertThrows(IllegalVoteTimeException.class, () -> service.vote(RESTAURANT1_ID, USER1_ID));
    }

    @Test
    public void createWithException() {
        validateRootCause(() -> service.create(new Vote(null, null), RESTAURANT1_ID, USER1_ID), ConstraintViolationException.class);
    }
}
