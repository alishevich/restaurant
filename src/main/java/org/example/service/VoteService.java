package org.example.service;

import org.example.model.Vote;
import org.example.repository.RestaurantRepository;
import org.example.repository.UserRepository;
import org.example.repository.VoteRepository;
import org.example.util.exception.IllegalVoteTimeException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.example.util.ValidationUtil.checkNotFoundWithId;

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
       return voteRepository.getByDate(date, userId);
    }

    public List<Vote> getAllWithRestaurant(LocalDate date) {
        return voteRepository.getAllWithRestaurantByDate(date);
    }

    public List<Vote> getAllForRestaurant(LocalDate date, int restaurantId) {
        return checkNotFoundWithId(voteRepository.getAllForRestaurant(date, restaurantId), restaurantId);
    }

    public Vote getWithRestaurant(LocalDate date, int userId) {
        return voteRepository.getWithRestaurant(date, userId);
    }

    public void vote(int restaurantId, int userId) {
        LocalTime votingTime = LocalTime.now();
        boolean isTimeLimit = votingTime.isBefore(deadline);
        LocalDate voteDate = LocalDate.now();
        Vote lastVote = get(voteDate, userId);
        if (isTimeLimit && lastVote == null) {
            Vote newVote = new Vote(null, voteDate);
            create(newVote, restaurantId, userId);
        } else if (isTimeLimit) {
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
        checkNotFoundWithId(voteRepository.save(vote), vote.id());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(voteRepository.delete(id, userId) != 0, id);
    }

    public void setTimeLimitForVote(LocalTime time) {
        deadline = time;
    }
}
