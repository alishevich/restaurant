package org.example.repository;

import org.example.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.date=:date AND v.user.id=:userId")
    Vote get(@Param("date") LocalDate date, @Param("userId") int userId);


    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.date=:date")
    List<Vote> getAllWithRestaurantByDate(@Param("date") LocalDate date);

}
