package org.example.service;

import org.example.model.Vote;
import org.example.repository.RestaurantRepository;
import org.example.repository.UserRepository;
import org.example.repository.VoteRepository;
import org.example.util.exception.IllegalVoteTimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class VoteService {
    private static LocalTime deadline = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository,
                       RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote get(LocalDate date, int userId) {
        LocalDate toDay =  (date != null) ? date : LocalDate.now();
        return voteRepository.get(toDay, userId);
    }

    @Transactional
    public void vote(int restaurantId, int userId) {
        LocalTime votingTime = LocalTime.now();
        boolean isDeadline = votingTime.isBefore(deadline);
        LocalDate voteDate = LocalDate.now();
        Vote lastVote = get(voteDate, userId);
        if (isDeadline && lastVote == null) {
            Vote newVote = new Vote(null, voteDate);
            create(newVote, restaurantId, userId);
        } else if (isDeadline) {
            update(lastVote, restaurantId);
        } else {
            throw  new IllegalVoteTimeException("The time to change vote has expired");
        }
    }

    public void create(Vote newVote, int restaurantId, int userId) {
        Assert.notNull(newVote, "vote must not be null");
        newVote.setRestaurant(restaurantRepository.getOne(restaurantId));
        newVote.setUser(userRepository.getOne(userId));
        voteRepository.save(newVote);
    }

    public void update(Vote vote, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        voteRepository.save(vote);
    }

    public void setDeadline(LocalTime time) {
        deadline = time;
    }
}
