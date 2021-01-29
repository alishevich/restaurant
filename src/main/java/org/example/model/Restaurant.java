package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "name_address_idx")})
public class Restaurant extends AbstractNamedEntity {
    @Column(name = "address", nullable = false)
    @NotBlank
    private String address;

    @Column(name = "phone", nullable = false)
    @NotBlank
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Telephone number should start with country code, then 10 digits")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Menu> menus;

    public Restaurant() {}

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getAddress(), r.getPhone());
    }

    public Restaurant(Integer id, String name, String address, String phone) {
        super(id, name);
        this.address = address;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}


