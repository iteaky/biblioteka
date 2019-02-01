package service;

import dao.h2_dao_implimintation.BookDaoImpl;
import entity.BookEntity;
import models.Author;
import models.Book;
import models.Publishing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private BookDaoImpl bookDao;
    private AuthorService authorService;
    private PublishingService publishingService;

    public BookService(BookDaoImpl bookDao, AuthorService authorService, PublishingService publishingService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.publishingService = publishingService;
    }

    public Book findOne(Integer id) throws SQLException {
        BookEntity bookById = bookDao.getBookById(id);
        List<Author> authorsByIds = authorService.getAuthorsByIds(bookById.getAuthorsId());
        Publishing publishingById = publishingService.getOnePublishing(bookById.getPublishingId());
        Book book = new Book(bookById.getName(), bookById.getDateOfRealise(), authorsByIds, publishingById);
        book.setId(bookById.getId());
        return book;
    }

    public List<Book> findAll() throws SQLException {
        List<BookEntity> allBooks = bookDao.getAllBooks();
        ArrayList<Book> books = new ArrayList<>();
        for (BookEntity book : allBooks) {
            books.add(createBook(book));
        }
        return books;
    }

    private Book createBook(BookEntity book) throws SQLException {
        List<Author> authorsByIds = authorService.getAuthorsByIds(book.getAuthorsId());
        Publishing publishingById = publishingService.getOnePublishing(book.getPublishingId());
        Book book1 = new Book(book.getName(), book.getDateOfRealise(), authorsByIds, publishingById);
        book1.setId(book.getId());
        return book1;
    }

    private BookEntity createBookEntity(Book book) throws SQLException {
        List<Integer> collect = book.getAuthor().stream().map(Author::getId).collect(Collectors.toList());
        return new BookEntity(book.getId(), book.getName(),
                book.getDateOfRealise(), collect, book.getPublishing().getId());
    }

    public boolean deleteBook(Integer id) throws SQLException {
        return bookDao.deleteBookById(id);
    }

    public Book addBook(BookEntity book) throws SQLException {
        BookEntity createdBookEntity = bookDao.addBook(book);
        return createBook(createdBookEntity);
    }

    private Book updateBook(Book book) throws SQLException {
        BookEntity bookFromDB = bookDao.getBookById(book.getId());
        BookEntity bookFromFE = createBookEntity(book);
        BookEntity bookForUpdate = new BookEntity();
        bookForUpdate.setId(book.getId());
        if (!bookFromDB.getName().equals(bookFromFE.getName())) {
            bookForUpdate.setName(bookFromFE.getName());
        }
        if (!bookFromDB.getDateOfRealise().equals(bookForUpdate.getDateOfRealise())) {
            bookForUpdate.setDateOfRealise(bookFromFE.getDateOfRealise());
        }
        if (!bookFromDB.getPublishingId().equals(bookFromFE.getPublishingId())) {
            bookForUpdate.setPublishingsId(bookFromFE.getPublishingId());
        }
//        if (!bookFromDB.getAuthorId().equals(bookFromFE.getAuthorId())) {
//            bookForUpdate.setAuthorId(bookFromFE.getAuthorId());
//        }
        boolean complete = bookDao.updateBook(bookForUpdate);
        if (complete) return book;
        else throw new RuntimeException("update failed");
    }
}
