package org.example.util;

import org.example.model.Vote;
import org.example.to.VoteTo;

public class VoteUtil {

    /*
    public static Vote createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), userTo.getCaloriesPerDay(), Role.USER);
    }

     */

    public static VoteTo asTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDate(), vote.getRestaurant().getId(), vote.getUser().getId());
    }
}
