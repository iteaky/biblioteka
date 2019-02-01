package servlet;


import dao.h2_dao_implimintation.AuthorDaoImpl;
import dao.h2_dao_implimintation.BookDaoImpl;
import dao.h2_dao_implimintation.PublishingDaoImpl;
import entity.BookEntity;
import entity.PublishingEntity;
import service.AuthorService;
import service.BookService;
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
import java.util.stream.Collectors;

@WebServlet(name = "PublishingServlet", value = {"/PublishingServlet"})
public class PublishingServlet extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        PublishingService publishingService = new PublishingService(new PublishingDaoImpl());

        try {
            String name = request.getParameter("name");
            String city = request.getParameter("city");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            PublishingEntity publishingEntity = new PublishingEntity(name, city, phone, email);
            publishingService.addPublishing(publishingEntity);
            session.setAttribute("authors", getAuthorService().getAllAuthors());
            session.setAttribute("publishings", getPublishingService().getAll());
            request.getRequestDispatcher("addBook.view").forward(request, response);

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
