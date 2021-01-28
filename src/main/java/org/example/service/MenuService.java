package org.example.service;

import org.example.model.Dish;
import org.example.model.Menu;
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

    public Menu get(int id, int restaurantId) {
       return checkNotFoundWithId(
               menuRepository.findById(id)
                 .filter(menu -> menu.getRestaurant().getId() == restaurantId)
                 .orElse(null),
               id);
    }

    public Menu getWithDishes(int id, int restaurantId) {
       return checkNotFoundWithId(menuRepository.getWithDishes(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(menuRepository.delete(id, restaurantId) != 0, id);
    }

    public List<Menu> getBetweenInclusiveWithDishes(LocalDate startDate,LocalDate endDate, int restaurantId) {
        return menuRepository.getBetweenInclusiveWithDishes(startDate, endDate, restaurantId);
    }

    public List<Menu> getAllWithDishes(int restaurantId) {
        return menuRepository.getAllWithDishes(restaurantId);
    }

    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "meal must not be null");
        checkNotFoundWithId(createWithDishes(menu, restaurantId), menu.getId());
    }

    public Menu createWithDishes(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        List<Dish> dishes = menu.getDishes().stream()
                .peek(dish -> dish.setMenu(menu))
                .collect(Collectors.toList());
        menu.setDishes(dishes);
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return menuRepository.save(menu);
    }

}
