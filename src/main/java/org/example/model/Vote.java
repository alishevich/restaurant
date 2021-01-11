package org.example.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {
    private Integer restaurantId;
    private Integer menuId;
    private LocalDateTime votingDate;
    private Integer countVotes;

    public Vote(Integer id, Integer restaurantId, Integer menuId, LocalDateTime dateTime, Integer countVotes) {
        super(id);
        this.restaurantId = restaurantId;
        this.menuId = menuId;
        this.votingDate = dateTime;
        this.countVotes = countVotes;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(LocalDateTime votingDate) {
        this.votingDate = votingDate;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getCountVotes() {
        return countVotes;
    }

    public void setCountVotes(Integer countVotes) {
        this.countVotes = countVotes;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", menuId=" + menuId +
                ", votingDate=" + votingDate +
                ", countVotes=" + countVotes +
                '}';
    }
}
