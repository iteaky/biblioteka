package servlet;


import dao.h2_dao_implimintation.AuthorDaoImpl;
import dao.h2_dao_implimintation.BookDaoImpl;
import dao.h2_dao_implimintation.PublishingDaoImpl;
import entity.BookEntity;
import models.Author;
import models.Book;
import models.Publishing;
import service.AuthorService;
import service.BookService;
import service.ConnectionUtil;
import service.PublishingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "BookServlet", value = {"/BookServlet"})
public class BookServlet extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        BookService bookService = getBookService();

        if ("true".equalsIgnoreCase(request.getParameter("addAuthor"))) {
            request.getRequestDispatcher("addAuthor.view").forward(request, response);

        } else if ("true".equalsIgnoreCase(request.getParameter("addPublishing"))) {
            request.getRequestDispatcher("addPublishing.view").forward(request, response);

        } else try {
            String bookName = request.getParameter("firstname");
            LocalDate releaseDate = LocalDate.parse(request.getParameter("releaseDate"), DateTimeFormatter.ofPattern("DD-mm-yyyy"));
            Integer publishingId = Integer.valueOf(request.getParameter("publishing"));
            List<Integer> authorId = Stream.of(request.getParameterValues("author")).map(Integer::valueOf).collect(Collectors.toList());

            BookEntity book = new BookEntity(bookName, releaseDate, authorId, publishingId);
            bookService.addBook(book);
            session.setAttribute("books", bookService.findAll());
            request.getRequestDispatcher("main.view").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BookService getBookService() {
        BookDaoImpl bookDao = new BookDaoImpl();
        AuthorDaoImpl authorDao = new AuthorDaoImpl();
        PublishingDaoImpl publishingDao = new PublishingDaoImpl();
        AuthorService authorService = new AuthorService(authorDao);
        PublishingService publishingService = new PublishingService(publishingDao);
        return new BookService(bookDao, authorService, publishingService);
    }

    private AuthorService getAuthorService() {
        AuthorDaoImpl authorDao = new AuthorDaoImpl();
        return new AuthorService(authorDao);
    }

    private PublishingService getPublishingService() {
        PublishingDaoImpl publishingDao = new PublishingDaoImpl();
        return new PublishingService(publishingDao);
    }
}
