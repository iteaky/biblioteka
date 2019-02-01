package entity;

import java.util.List;

public class PublishingEntity {
    private Integer id;
    private String name;
    private String city;
    private String phone;
    private String email;

    public PublishingEntity() {
    }

    public PublishingEntity(Integer id, String name, String city, String phone, String email) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    public PublishingEntity(String name, String city, String phone, String email) {
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
