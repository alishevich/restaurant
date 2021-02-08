package org.example.repository;

import org.example.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m WHERE r.id=:id AND m.date=:date")
    Restaurant getWithMenusByDate(@Param("id") int id, @Param("date") LocalDate date);

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m WHERE m.date=:date ORDER BY r.name")
    List<Restaurant> getAllWithMenusByDate(@Param("date") LocalDate date);

    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m WHERE m.date=:date ORDER BY r.name")
    List<Restaurant> getAllActiveByDate(@Param("date") LocalDate date);

}
