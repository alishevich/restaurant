package org.example.service;

import org.example.model.Dish;
import org.example.model.Menu;
import org.example.model.Restaurant;
import org.example.repository.MenuRepository;
import org.example.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public void delete(int id) {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }

    public List<Menu> getAllWithDishes() {
        return menuRepository.findAll();
    }

    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(createWithDishes(menu, restaurantId), menu.getId());
    }

    public Menu createWithDishes(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        List<Dish> dishes = menu.getDishes();
        if (dishes != null && !dishes.isEmpty()) {
            menu.setDishes(dishes.stream()
                    .peek(dish -> dish.setMenu(menu))
                    .collect(Collectors.toList()));
        }
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getOne(restaurantId), restaurantId);
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

}
