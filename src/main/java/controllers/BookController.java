package controllers;

import dao.h2_dao_implimintation.AuthorDaoImpl;
import dao.h2_dao_implimintation.BookDaoImpl;
import dao.h2_dao_implimintation.PublishingDaoImpl;
import entity.AuthorEntity;
import entity.BookEntity;
import entity.PublishingEntity;
import models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import service.AuthorService;
import service.BookService;
import service.PublishingService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class BookController {


    @GetMapping({"/"})
    public String welcome(Map<String, Object> model) throws SQLException {
        List<Book> all = getBookService().findAll();
        model.put("books", all);
        return "main";
    }

    @GetMapping({"/MainServlet"})
    public String showAddBook(Map<String, Object> model) throws SQLException {
        model.put("authors", authorService().getAllAuthors());
        model.put("publishings", publishingService().getAll());
        return "addBook";
    }

    @GetMapping({"/Book"})
    public String addBook(@RequestParam("firstname") String bookName, @RequestParam("releaseDate") String date, @RequestParam("author") String[] author, @RequestParam("publishing") Integer publishingId, Map<String, Object> model) throws SQLException {
        LocalDate releaseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("DD-mm-yyyy"));
        List<Integer> authorId = Stream.of(author).map(Integer::valueOf).collect(Collectors.toList());
        BookEntity book = new BookEntity(bookName, releaseDate, authorId, publishingId);
        getBookService().addBook(book);
        model.put("books", getBookService().findAll());
        return "main";
    }

    private BookService getBookService() {
        BookDaoImpl bookDao = new BookDaoImpl();
        AuthorDaoImpl authorDao = new AuthorDaoImpl();
        PublishingDaoImpl publishingDao = new PublishingDaoImpl();
        AuthorService authorService = new AuthorService(authorDao);
        PublishingService publishingService = new PublishingService(publishingDao);
        return new BookService(bookDao, authorService, publishingService);
    }

    private AuthorService authorService() {
        return new AuthorService(new AuthorDaoImpl());
    }

    private PublishingService publishingService() {
        return new PublishingService(new PublishingDaoImpl());
    }
}

