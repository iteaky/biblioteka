package servlet;


import dao.h2_dao_implimintation.AuthorDaoImpl;
import dao.h2_dao_implimintation.BookDaoImpl;
import dao.h2_dao_implimintation.PublishingDaoImpl;
import entity.AuthorEntity;
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

@WebServlet(name = "AuthorServlet", value = {"/AuthorServlet"})
public class AuthorServlet extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        AuthorService authorService = new AuthorService(new AuthorDaoImpl());

        try {
            String name = request.getParameter("name");
            String secondName = request.getParameter("secondName");
            LocalDate birthday = LocalDate.parse(request.getParameter("birthday"), DateTimeFormatter.ofPattern("DD-mm-yyyy"));
            AuthorEntity authorEntity = new AuthorEntity(name,secondName, birthday);
            authorService.addAuthor(authorEntity);
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
