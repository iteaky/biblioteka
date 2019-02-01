package controllers;

import dao.h2_dao_implimintation.AuthorDaoImpl;
import dao.h2_dao_implimintation.PublishingDaoImpl;
import entity.PublishingEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.AuthorService;
import service.PublishingService;

import java.sql.SQLException;
import java.util.Map;

@Controller
public class PublishingController {

    @GetMapping({"/Publishing"})
    public String openPublishing() {
        return "addPublishing";
    }

    @GetMapping({"/AddPublishing"})
    public String addPublishing(@RequestParam("name") String name, @RequestParam("city") String city, @RequestParam("phone") String phone, @RequestParam("email") String email, Map<String, Object> model) throws SQLException {
        PublishingEntity publishingEntity = new PublishingEntity(name, city, phone, email);
        publishingService().addPublishing(publishingEntity);
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
