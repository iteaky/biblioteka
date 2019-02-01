package dao.h2_dao_implimintation;

import dao.BookDao;
import entity.BookEntity;
import service.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static service.ConnectionUtil.SQL_FOR_ID;

public class BookDaoImpl implements BookDao {
    @Override
    public BookEntity getBookById(Integer id) throws SQLException {
        String sqlFirstSelect = "SELECT * FROM \"biblioteka\".BOOK WHERE ID = ?";
        String sqlSecondSelect = "SELECT AuthorId FROM \"biblioteka\".AuthorAndBook WHERE BookId = ?";
        ArrayList<Integer> authorsId = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement(sqlFirstSelect);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Date dateOfRealise = resultSet.getDate("dateOfRealise");
        String name = resultSet.getString("name");
        Integer publishingId = resultSet.getInt("publishingId");

        PreparedStatement statement2 = connection.prepareStatement(sqlSecondSelect);
        statement2.setInt(1, id);
        ResultSet resultSet2 = statement.executeQuery();
        while (resultSet2.next()) {
            Integer authorId = resultSet.getInt("AuthorId");
            authorsId.add(authorId);
        }

        connection.close();
        return new BookEntity(id, name, dateOfRealise.toLocalDate(), authorsId, publishingId);
    }

    @Override
    public List<BookEntity> getAllBooks() throws SQLException {
        String sqlSelect = "SELECT * FROM \"biblioteka\".book";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlSelect);
        ArrayList<BookEntity> books = new ArrayList<>();
        while (resultSet.next()) {
            ArrayList<Integer> authorsId = new ArrayList<>();
            Integer id = resultSet.getInt("id");
            Date dateOfRealise = resultSet.getDate("dateOfRealise");
            String name = resultSet.getString("name");
            Integer publishingId = resultSet.getInt("publishingId");

            String sqlSecondSelect = "SELECT AuthorId FROM \"biblioteka\".AuthorAndBook WHERE BookId = ?";
            PreparedStatement statement2 = connection.prepareStatement(sqlSecondSelect);
            statement2.setInt(1, id);
            ResultSet resultSet2 = statement2.executeQuery();
            while (resultSet2.next()) {
                Integer authorId = resultSet2.getInt("AuthorId");
                authorsId.add(authorId);
            }
            books.add(new BookEntity(id, name, dateOfRealise.toLocalDate(), authorsId, publishingId));
        }
        connection.close();
        return books;
    }

    @Override
    public BookEntity addBook(BookEntity book) throws SQLException {
        Integer id = null;
        String sqlSelect = "INSERT INTO \"biblioteka\".book (id, name, dateOfRealise, publishingId) VALUES (?,?,?,?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlSelect);
        ResultSet resultSet = connection.createStatement().executeQuery(SQL_FOR_ID);
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        book.setId(id);
        statement.setInt(1, book.getId());
        statement.setString(2, book.getName());
        statement.setDate(3, Date.valueOf(book.getDateOfRealise()));
        statement.setInt(4, book.getPublishingId());
        statement.executeUpdate();
        for (Integer integer : book.getAuthorsId()) {
            ResultSet resultSetForId = connection.createStatement().executeQuery(SQL_FOR_ID);
            int abId = 0;
            while (resultSetForId.next()) {
                abId = resultSetForId.getInt("id");
            }
                String sqlSecondSelect = "INSERT INTO \"biblioteka\".AuthorAndBook (id, bookId, authorId) VALUES (?,?,?)";
                PreparedStatement statementTwo = connection.prepareStatement(sqlSecondSelect);
                statementTwo.setInt(1, abId);
                statementTwo.setInt(2, book.getId());
                statementTwo.setInt(3, integer);
                statementTwo.execute();

        }
            connection.close();
            return book;

        }

        @Override
        public boolean deleteBookById (Integer id) throws SQLException {
            String sqlSelect = "DELETE FROM \"biblioteka\".book WHERE id = ?";
            String sqlSecondSelect = "DELETE FROM \"biblioteka\".AuthorAndBook WHERE bookId = ?";

            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            PreparedStatement statement2 = connection.prepareStatement(sqlSecondSelect);
            statement.setInt(1, id);
            statement2.setInt(1, id);
            statement.execute();
            boolean execute = statement2.execute();
            connection.close();
            return execute;
        }

        @Override
        public boolean updateBook (BookEntity book) throws SQLException {
            Connection connection = ConnectionUtil.getConnection();
            boolean execute = false;
            if (book.getName() != null & !book.getName().equals("")) {
                String sqlSelect = "UPDATE \"biblioteka\".book SET name = ? WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(sqlSelect);
                statement.setString(1, book.getName());
                statement.setInt(2, book.getId());
                execute = statement.execute();
            }
            if (book.getDateOfRealise() != null) {
                String sqlSelect = "UPDATE \"biblioteka\".book SET dateOfRealise = ? WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(sqlSelect);
                statement.setDate(1, Date.valueOf(book.getDateOfRealise()));
                statement.setInt(2, book.getId());
                execute = statement.execute();
            }
            if (!book.getAuthorsId().isEmpty()) {
                String sqlSecondSelect = "DELETE FROM \"biblioteka\".AuthorAndBook WHERE bookId = ?";
                PreparedStatement statement = connection.prepareStatement(sqlSecondSelect);
                statement.setInt(1, book.getId());
                execute = statement.execute();
                for (Integer authorId : book.getAuthorsId()) {
                    String sqlThirdSelect = "INSERT INTO \"biblioteka\".AuthorAndBook (bookId, authorId) VALUES (?,?)";
                    PreparedStatement statementTwo = connection.prepareStatement(sqlThirdSelect);
                    statementTwo.setInt(1, book.getId());
                    statementTwo.setInt(2, authorId);
                    statementTwo.execute();
                }
            }
            if (book.getPublishingId() != null) {
                String sqlSelect = "UPDATE \"biblioteka\".book SET publishingId = ? WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(sqlSelect);
                statement.setInt(1, book.getPublishingId());
                statement.setInt(2, book.getId());
                execute = statement.execute();
            }
            connection.close();
            return execute;
        }
    }
