package org.example.service;

import org.example.model.Menu;
import org.example.repository.MenuRepository;
import org.example.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

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
        return menuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    public Menu getWithDishes(int id, int restaurantId) {
        return  menuRepository.getWithDishes(id, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(menuRepository.delete(id, restaurantId) != 0, id);
    }

    public List<Menu> getBetweenInclusive(LocalDate startDate,LocalDate endDate, int restaurantId) {
        return menuRepository.getBetween(startDate, endDate, restaurantId);
    }

    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAll(restaurantId);
    }

    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "meal must not be null");
        checkNotFoundWithId(create(menu, restaurantId), menu.getId());
    }

    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return menuRepository.save(menu);
    }

}
