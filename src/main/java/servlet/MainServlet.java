package servlet;


import dao.h2_dao_implimintation.AuthorDaoImpl;
import dao.h2_dao_implimintation.BookDaoImpl;
import dao.h2_dao_implimintation.PublishingDaoImpl;
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
import java.util.Collections;
import java.util.stream.Collectors;

//@WebServlet(name = "MainServlet", value = {"", "/MainServlet"})
public class MainServlet extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String book = request.getParameter("book");
        HttpSession session = request.getSession();
        if ("add".equalsIgnoreCase(book)) {
            try {
                session.setAttribute("authors", getAuthorService().getAllAuthors());
                session.setAttribute("publishings", getPublishingService().getAll().stream().distinct().collect(Collectors.toList()));
                request.getRequestDispatcher("addBook.view").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else {
            BookService bookService = getBookService();

            try {
                session.setAttribute("books", bookService.findAll());
                request.getRequestDispatcher("main.view").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        return new AuthorService(new AuthorDaoImpl());
    }

    private PublishingService getPublishingService() {
        return new PublishingService(new PublishingDaoImpl());
    }

}
