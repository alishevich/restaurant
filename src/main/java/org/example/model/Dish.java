package org.example.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "amount", nullable = false)
    private Integer amount;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "menu_id", nullable = false)
     @OnDelete(action = OnDeleteAction.CASCADE)
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
