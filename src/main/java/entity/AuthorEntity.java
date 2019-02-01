package entity;

import java.time.LocalDate;
import java.util.List;

public class AuthorEntity {
    private Integer id;
    private String name;
    private String secondName;
    private LocalDate birthday;

    public AuthorEntity() {
    }

    public AuthorEntity(Integer id, String name, String secondName, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.birthday = birthday;
    }
    public AuthorEntity(String name, String secondName, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.birthday = birthday;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
