package org.example.model;

public class Restaurant extends AbstractNamedEntity {
    private String address;
    private String phone;
    private Integer countVotes;

    public Restaurant(Integer id, String name, String address, String phone, Integer countVotes) {
        super(id, name);
        this.address = address;
        this.phone = phone;
        this.countVotes = countVotes;
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

    public Integer getCountVotes() {
        return countVotes;
    }

    public void setCountVotes(Integer countVotes) {
        this.countVotes = countVotes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", countVotes=" + countVotes +
                '}';
    }
}


