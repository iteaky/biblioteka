package dao;

import entity.BookEntity;
import models.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    BookEntity getBookById(Integer id) throws SQLException;

    List getAllBooks() throws SQLException;

    BookEntity addBook(BookEntity book) throws SQLException;

    boolean deleteBookById(Integer id) throws SQLException;

    boolean updateBook(BookEntity book) throws SQLException;

}
