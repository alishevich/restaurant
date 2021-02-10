package org.example.service;

import org.example.model.Restaurant;
import org.example.model.Vote;
import org.example.repository.RestaurantRepository;
import org.example.repository.VoteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.example.util.RestaurantUtil.addVotes;
import static org.example.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    public RestaurantService(RestaurantRepository repository, VoteRepository voteRepository) {
        this.restaurantRepository = repository;
        this.voteRepository = voteRepository;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(SORT_NAME);
    }

    public List<Restaurant> getAllActiveByDate(LocalDate date) {
        LocalDate toDay =  (date != null) ? date : LocalDate.now();
        return restaurantRepository.getAllActiveByDate(toDay);
    }

    public Restaurant getWithMenusByDate(int id, LocalDate date) {
        LocalDate toDay =  (date != null) ? date : LocalDate.now();
        return checkNotFoundWithId(restaurantRepository.getWithMenusByDate(id, toDay), id);
    }

    public List<Restaurant> getAllWithMenus(LocalDate date) {
        LocalDate toDay =  (date != null) ? date : LocalDate.now();
        return restaurantRepository.getAllWithMenusByDate(toDay);
    }

    @Transactional
    public List<Restaurant> getAllWithVotes(LocalDate date) {
        LocalDate toDay =  (date != null) ? date : LocalDate.now();
        List<Restaurant> restaurants = getAllActiveByDate(toDay);
        if (!restaurants.isEmpty()) {
            List<Vote> votes = voteRepository.getAllWithRestaurantByDate(toDay);
            addVotes(restaurants, votes);
        }
        return restaurants;
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }
}
