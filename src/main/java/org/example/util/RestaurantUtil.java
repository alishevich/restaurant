package org.example.util;

import org.example.model.Restaurant;
import org.example.model.Vote;
import org.example.to.RestaurantTo;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static List<Restaurant> addVotes(List<Restaurant> restaurants, List<Vote> votes) {
        Map<Integer, List<Vote>> votesGroup = votes.stream()
                .collect(Collectors.groupingBy(vote -> vote.getRestaurant().getId()));

        restaurants.forEach(r ->
                r.setVotes(Optional.ofNullable(votesGroup.get(r.getId())).orElse(Collections.EMPTY_LIST)));

        return restaurants;
    }

    public static List<RestaurantTo> getTos(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .sorted(Comparator.comparingInt(RestaurantTo::getVotesCounter)
                .reversed())
                .collect(Collectors.toList());
    }

    public static RestaurantTo createTo(Restaurant r) {
        return new RestaurantTo(r.getId(), r.getName(), r.getAddress(), r.getPhone(), r.getVotes().size());
    }
}

