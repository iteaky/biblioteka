package dao;

import entity.AuthorEntity;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDao {
    AuthorEntity getAuthorById(Integer id) throws SQLException;

    List getAllAuthors() throws SQLException;

    List getAuthorsByIds(List<Integer> ids) throws SQLException;

    AuthorEntity addAuthor(AuthorEntity author) throws SQLException;

    boolean deleteAuthorById(Integer id) throws SQLException;

    boolean updateAuthor(AuthorEntity author) throws SQLException;

}
