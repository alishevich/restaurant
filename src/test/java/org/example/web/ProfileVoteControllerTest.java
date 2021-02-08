package org.example.web;

import org.example.model.Vote;
import org.example.service.VoteService;
import org.example.util.VoteUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.example.testdata.RestaurantTestData.RESTAURANT1_ID;
import static org.example.testdata.UserTestData.USER1_ID;

import static org.example.testdata.VoteTestData.*;
import static org.example.web.SecurityUtil.authUserId;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileVoteController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    void getWithRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "with-restaurant")
                .param("date", "2021-01-25")
                .param("userId", String.valueOf(USER1_ID)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.asTo(vote1)));
    }

    @Test
    void vote() throws Exception {
        service.setTimeLimitForVote(LocalTime.now().plusHours(1));
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.patch(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT1_ID)));

        Vote created = service.get(LocalDate.now(), authUserId());
        int newId = created.getId();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
    }
}