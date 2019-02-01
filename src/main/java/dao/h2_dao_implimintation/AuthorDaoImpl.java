package dao.h2_dao_implimintation;

import dao.AuthorDao;
import dao.AuthorDao;
import entity.AuthorEntity;
import entity.AuthorEntity;
import service.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static service.ConnectionUtil.SQL_FOR_ID;

public class AuthorDaoImpl implements AuthorDao {
    @Override
    public AuthorEntity getAuthorById(Integer id) throws SQLException {
        String sqlSelect = "SELECT * FROM \"biblioteka\".AUTHOR WHERE ID = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlSelect);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        connection.close();
        Date birthday = resultSet.getDate("birthday");
        String name = resultSet.getString("name");
        String secondName = resultSet.getString("secondName");
        return new AuthorEntity(id, name, secondName, birthday.toLocalDate());
    }

    @Override
    public List<AuthorEntity> getAllAuthors() throws SQLException {
        String sqlSelect = "SELECT * FROM \"biblioteka\".AUTHOR";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlSelect);
        ArrayList<AuthorEntity> authors = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Date birthday = resultSet.getDate("birthday");
            String name = resultSet.getString("name");
            String secondName = resultSet.getString("secondName");
            authors.add(new AuthorEntity(id, name, secondName, birthday.toLocalDate()));
        }
        connection.close();
        return authors;
    }

    @Override
    public List<AuthorEntity> getAuthorsByIds(List<Integer> ids) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        ArrayList<AuthorEntity> authors = new ArrayList<>();
        for (Integer id : ids) {
            String sqlSelect = "SELECT * FROM \"biblioteka\".AUTHOR WHERE ID IS ?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date birthday = resultSet.getDate("birthday");
                String name = resultSet.getString("name");
                String secondName = resultSet.getString("secondName");
                authors.add(new AuthorEntity(id, name, secondName, birthday.toLocalDate()));
            }
        }
        connection.close();
        return authors;

    }

    @Override
    public AuthorEntity addAuthor(AuthorEntity author) throws SQLException {
        Integer authorId = null;
        String sqlSelect = "INSERT INTO \"biblioteka\".author (id, name, secondName, birthday) VALUES (?,?,?,?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlSelect);
        ResultSet resultSet = connection.createStatement().executeQuery(SQL_FOR_ID);
        while (resultSet.next()) {
            authorId = resultSet.getInt("id");
        }
        author.setId(authorId);
        statement.setInt(1, author.getId());
        statement.setString(2, author.getName());
        statement.setString(3, author.getSecondName());
        statement.setDate(4, Date.valueOf(author.getBirthday()));
        statement.executeUpdate();
        connection.close();
        return author;

    }

    @Override
    public boolean deleteAuthorById(Integer id) throws SQLException {
        String sqlSelect = "DELETE FROM \"biblioteka\".author WHERE id = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlSelect);
        statement.setInt(1, id);
        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public boolean updateAuthor(AuthorEntity author) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        boolean execute = false;
        if (author.getName() != null & !author.getName().equals("")) {
            String sqlSelect= "UPDATE \"biblioteka\".author SET name = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, author.getName());
            statement.setInt(2, author.getId());
            execute = statement.execute();
        }
        if (author.getBirthday() != null) {
            String sqlSelect= "UPDATE \"biblioteka\".author SET birthday = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setDate(1, Date.valueOf(author.getBirthday()));
            statement.setInt(2, author.getId());
            execute = statement.execute();
        }
        if (author.getSecondName() != null & !author.getSecondName().equals("")) {
            String sqlSelect= "UPDATE \"biblioteka\".author SET secondName = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, author.getSecondName());
            statement.setInt(2, author.getId());
            execute = statement.execute();
        }
        connection.close();
        return execute;
    }
}
