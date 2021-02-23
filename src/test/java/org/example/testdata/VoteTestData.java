package org.example.testdata;

import org.example.TestMatcher;
import org.example.model.Vote;
import org.example.to.VoteTo;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.testdata.RestaurantTestData.restaurant1;
import static org.example.testdata.RestaurantTestData.restaurant2;
import static org.example.testdata.UserTestData.user1;
import static org.example.testdata.UserTestData.user2;

public class VoteTestData {
    public static final TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");
    public static final TestMatcher<VoteTo> VOTE_TO_MATCHER = TestMatcher.usingIgnoringFieldsComparator(VoteTo.class);
    public static TestMatcher<Vote> VOTE_WITH_RESTAURANT_MATCHER =
            TestMatcher.usingAssertions(Vote.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("restaurant.menus", "restaurant.votes",  "user").isEqualTo(e),
                    (a, e) -> {
                        assertThat(a).usingRecursiveComparison()
                                .ignoringFields("restaurant.menus", "restaurant.votes",  "user").isEqualTo(e);
                    });


    public static final int VOTE1_ID = 0;
    public static final LocalDate DATE = LocalDate.of(2021, Month.JANUARY, 25);
    public static final LocalDate VOTE_NOT_FOUND = LocalDate.of(2021, Month.JANUARY, 30);

    public static final Vote vote1 = new Vote(VOTE1_ID, LocalDate.of(2021, Month.JANUARY, 25));
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, LocalDate.of(2021, Month.JANUARY, 25));
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, LocalDate.of(2021, Month.JANUARY, 26));
    public static final Vote vote4 = new Vote(VOTE1_ID + 3, LocalDate.of(2021, Month.JANUARY, 26));
    public static final Vote vote5 = new Vote(VOTE1_ID + 4, LocalDate.now());

    static {
        vote1.setRestaurant(restaurant1);
        vote2.setRestaurant(restaurant1);
        vote3.setRestaurant(restaurant1);
        vote4.setRestaurant(restaurant2);
        vote5.setRestaurant(restaurant1);

        vote1.setUser(user1);
        vote2.setUser(user2);
        vote3.setUser(user1);
        vote4.setUser(user2);
        vote5.setUser(user1);
    }

    public static Vote getNew() {
        return new Vote(null, LocalDate.now());
    }

    public static Vote getNewWithRestAndUser() {
        Vote newVote = getNew();
        newVote.setRestaurant(restaurant1);
        newVote.setUser(user1);
        return newVote;
    }

    public static Vote getUpdated() {
        Vote updated = getNewWithRestAndUser();
        updated.setRestaurant(restaurant2);
        return updated;
    }
}
