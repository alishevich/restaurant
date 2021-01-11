package org.example.model;

import java.time.LocalDateTime;
import java.util.List;

public class Menu extends AbstractBaseEntity {
    private List<Dish> dishes;
    private LocalDateTime dateTime;

    public Menu(Integer id, List<Dish> dishes, LocalDateTime dateTime) {
        super(id);
        this.dishes = dishes;
        this.dateTime = dateTime;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dishes=" + dishes +
                ", dateTime=" + dateTime +
                '}';
    }
}
