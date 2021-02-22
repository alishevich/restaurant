package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "amount", nullable = false)
    @Range(min = 1, max = 1000)
    private Integer amount;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "menu_id", nullable = false)
     @NotNull
     @OnDelete(action = OnDeleteAction.CASCADE)
     @JsonIgnore
     private Menu menu;

    public Dish(){}

    public Dish(Integer id, String name, Integer amount) {
        super(id, name);
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
