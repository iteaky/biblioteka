package models;

import java.time.LocalDate;
import java.util.List;

public class Book {
    private Integer id;
    private String name;
    private LocalDate dateOfRealise;
    private List<Author> author;
    private Publishing publishing;

    public Book() {
    }


    public Book( String name, LocalDate dateOfRealise, List<Author> author, Publishing publishing) {
        this.name = name;
        this.dateOfRealise = dateOfRealise;
        this.author = author;
        this.publishing = publishing;
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

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public Publishing getPublishing() {
        return publishing;
    }

    public void setPublishing(Publishing publishing) {
        this.publishing = publishing;
    }
}
