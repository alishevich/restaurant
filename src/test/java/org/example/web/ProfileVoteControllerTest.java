package org.example.web;

import org.example.service.VoteService;
import org.example.util.VoteUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static org.example.testdata.RestaurantTestData.RESTAURANT1_ID;
import static org.example.testdata.UserTestData.USER1_ID;

import static org.example.testdata.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileVoteController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "with-restaurant")
                .param("date", "2021-01-25")
                .param("userId", String.valueOf(USER1_ID)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.asTo(vote1)));
    }

    @Test
    void vote() throws Exception {
        service.setDeadline(LocalTime.now().plusHours(1));

        perform(MockMvcRequestBuilders.patch(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT1_ID)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}