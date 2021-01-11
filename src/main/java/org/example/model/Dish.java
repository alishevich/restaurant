package org.example.model;

public class Dish extends AbstractNamedEntity {
    private Integer amount;

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

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
