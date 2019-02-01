package controllers;

import dao.h2_dao_implimintation.AuthorDaoImpl;
import dao.h2_dao_implimintation.PublishingDaoImpl;
import entity.AuthorEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.AuthorService;
import service.PublishingService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
public class AuthorController {
    @GetMapping({"/Author"})
    public String openAuthor() {
        return "addAuthor";
    }

    @GetMapping({"/AddAuthor"})
    public String addAuthor(@RequestParam("name") String name, @RequestParam("secondName") String secondName, @RequestParam("birthday") String date, Map<String, Object> model) throws SQLException {
        LocalDate birthday = LocalDate.parse(date, DateTimeFormatter.ofPattern("DD-mm-yyyy"));
        AuthorEntity authorEntity = new AuthorEntity(name, secondName, birthday);
        authorService().addAuthor(authorEntity);
        model.put("authors", authorService().getAllAuthors());
        model.put("publishings", publishingService().getAll());
        return "addBook";
    }

    private AuthorService authorService() {
        return new AuthorService(new AuthorDaoImpl());
    }

    private PublishingService publishingService() {
        return new PublishingService(new PublishingDaoImpl());
    }
}
