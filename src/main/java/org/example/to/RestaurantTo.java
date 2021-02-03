package org.example.to;

public class RestaurantTo extends BaseTo {

    private String name;
    private String address;
    private String phone;
    private int votesCounter;

    public RestaurantTo(Integer id, String name, String address, String phone, int votesCounter) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.votesCounter = votesCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVotesCounter() {
        return votesCounter;
    }

    public void setVotesCounter(int votesCounter) {
        this.votesCounter = votesCounter;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", votesCounter=" + votesCounter +
                '}';
    }
}
