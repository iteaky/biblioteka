package service;

import dao.h2_dao_implimintation.AuthorDaoImpl;
import entity.AuthorEntity;
import entity.BookEntity;
import models.Author;
import models.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorService {
    private AuthorDaoImpl authorDao;

    public AuthorService(AuthorDaoImpl authorDao) {
        this.authorDao = authorDao;
    }

    public Author getOneAuthor(Integer id) throws SQLException {
        AuthorEntity authorById = authorDao.getAuthorById(id);
        return new Author(authorById.getId(), authorById.getName(), authorById.getSecondName(), authorById.getBirthday());
    }

    public List<Author> getAuthorsByIds(List<Integer> ids) throws SQLException {
        List<AuthorEntity> authorsByIds = authorDao.getAuthorsByIds(ids);
        return authorsByIds.stream().map(this::createAuthor).collect(Collectors.toList());
    }
    public List<Author> getAllAuthors() throws SQLException {
        List<AuthorEntity> authorsByIds = authorDao.getAllAuthors();
        return authorsByIds.stream().map(this::createAuthor).collect(Collectors.toList());
    }

    public Author addAuthor(AuthorEntity author) throws SQLException {
        AuthorEntity authorEntityWithId = authorDao.addAuthor(author);
        return createAuthor(authorEntityWithId);
    }

    public Boolean deleteAuthor(Integer id) throws SQLException {
        return authorDao.deleteAuthorById(id);
    }

    public Author updateAuthor(Author author) throws SQLException {
        AuthorEntity authorFromDB = authorDao.getAuthorById(author.getId());
        AuthorEntity authorFromFE = createAuthorEntity(author);
        AuthorEntity authorForUpdate = new AuthorEntity();
        authorForUpdate.setId(author.getId());
        if (!authorFromDB.getName().equals(authorFromFE.getName())) {
            authorForUpdate.setName(authorFromFE.getName());
        }
        if (!authorFromDB.getName().equals(authorForUpdate.getName())) {
            authorForUpdate.setName(authorFromFE.getName());
        }
        if (!authorFromDB.getSecondName().equals(authorFromFE.getSecondName())) {
            authorForUpdate.setSecondName(authorFromFE.getSecondName());
        }
        if (authorFromDB.getBirthday().equals(authorFromFE.getBirthday())) {
            authorForUpdate.setBirthday(authorFromFE.getBirthday());
        }
        boolean complete = authorDao.updateAuthor(authorForUpdate);
        if (complete) return author;
        else throw new RuntimeException("update failed");

    }


    private Author createAuthor(AuthorEntity entity) {
        return new Author(entity.getId(), entity.getName(), entity.getSecondName(), entity.getBirthday());
    }
    private AuthorEntity createAuthorEntity(Author entity) {
        return new AuthorEntity(entity.getId(), entity.getName(), entity.getSecondName(), entity.getBirthday());
    }
}
