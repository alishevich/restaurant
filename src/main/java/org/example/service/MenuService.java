package org.example.service;

import org.example.model.Dish;
import org.example.model.Menu;
import org.example.model.Restaurant;
import org.example.repository.MenuRepository;
import org.example.repository.RestaurantRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static org.example.util.MenuUtil.addDishes;
import static org.example.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu getWithDishes(int id) {
       return checkNotFoundWithId(menuRepository.getWithDishes(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }

    public List<Menu> getAllWithDishes() {
        return menuRepository.findAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(createWithDishes(menu, restaurantId), menu.getId());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    public Menu createWithDishes(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        List<Dish> dishes = menu.getDishes();
        if (dishes != null && !dishes.isEmpty()) {
            addDishes(menu, dishes);
        }
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getOne(restaurantId), restaurantId);
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }
}
