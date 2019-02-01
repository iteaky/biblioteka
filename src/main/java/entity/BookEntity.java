package entity;

import java.time.LocalDate;
import java.util.List;

public class BookEntity {
    private Integer id;
    private String name;
    private LocalDate dateOfRealise;
    private List<Integer> authorsId;
    private Integer publishingId;

    public BookEntity() {
    }

    public BookEntity(Integer id, String name, LocalDate dateOfRealise, List<Integer> authorsId, Integer publishingsId) {
        this.id = id;
        this.name = name;
        this.dateOfRealise = dateOfRealise;
        this.authorsId = authorsId;
        this.publishingId = publishingsId;
    }
    public BookEntity(String name, LocalDate dateOfRealise, List<Integer> authorsId, Integer publishingsId) {
        this.name = name;
        this.dateOfRealise = dateOfRealise;
        this.authorsId = authorsId;
        this.publishingId = publishingsId;
    }

    public Integer getPublishingId() {
        return publishingId;
    }

    public void setPublishingsId(Integer publishingsId) {
        this.publishingId = publishingId;
    }

    public List<Integer> getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(List<Integer> authorsId) {
        this.authorsId = authorsId;
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

    public LocalDate getDateOfRealise() {
        return dateOfRealise;
    }

    public void setDateOfRealise(LocalDate dateOfRealise) {
        this.dateOfRealise = dateOfRealise;
    }
}
