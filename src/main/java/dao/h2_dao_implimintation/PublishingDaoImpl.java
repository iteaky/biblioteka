package dao.h2_dao_implimintation;

import dao.PublishingDao;
import entity.PublishingEntity;
import models.Publishing;
import service.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static service.ConnectionUtil.SQL_FOR_ID;

public class PublishingDaoImpl implements PublishingDao {
    @Override
    public PublishingEntity getPublishingById(Integer id) throws SQLException {
        String sqlSelect = "SELECT * FROM \"biblioteka\".publishing WHERE ID = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlSelect);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        String name = null;
        String city = null;
        String phone = null;
        String email = null;
        while (resultSet.next()) {
            name = resultSet.getString("name");
            city = resultSet.getString("city");
            phone = resultSet.getString("phone");
            email = resultSet.getString("email");
        }
        connection.close();
        return new PublishingEntity(id, name, city, phone, email);
    }

    @Override
    public List<PublishingEntity> getAllPublishings() throws SQLException {
        String sqlSelect = "SELECT * FROM \"biblioteka\".publishing";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlSelect);
        ArrayList<PublishingEntity> publishings = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String city = resultSet.getString("city");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            publishings.add(new PublishingEntity(id, name, city, phone, email));
        }
        connection.close();
        return publishings;
    }

    @Override
    public List<PublishingEntity> getPublishingsByIds(List<Integer> ids) throws SQLException {
        String publishingIds = ids.stream().map(Object::toString).collect(Collectors.joining(","));
        String sqlSelect = "SELECT * FROM \"biblioteka\".PUBLISHING WHERE ID IN (?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlSelect);
        statement.setString(1, publishingIds);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<PublishingEntity> publishing = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String city = resultSet.getString("city");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            publishing.add(new PublishingEntity(id, name, city, phone, email));
        }
        connection.close();
        return publishing;
    }

    @Override
    public PublishingEntity addPublishing(PublishingEntity publishing) throws SQLException {
        Integer id = null;
        String sqlSelect = "INSERT INTO \"biblioteka\".PUBLISHING (ID, NAME, CITY, PHONE, EMAIL) VALUES (?,?,?,?,?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlSelect);

        ResultSet resultSet = connection.createStatement().executeQuery(SQL_FOR_ID);
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        publishing.setId(id);

        statement.setInt(1, publishing.getId());
        statement.setString(2, publishing.getName());
        statement.setString(3, publishing.getCity());
        statement.setString(4, publishing.getPhone());
        statement.setString(5, publishing.getEmail());
        statement.executeUpdate();
        connection.close();
        return publishing;

    }

    @Override
    public boolean deletePublishingById(Integer id) throws SQLException {
        String sqlSelect = "DELETE FROM \"biblioteka\".publishing WHERE id = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlSelect);
        statement.setInt(1, id);
        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public boolean updatePublishing(PublishingEntity publishing) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        boolean execute = false;
        if (isNotEmpty(publishing.getName())) {
            String sqlSelect= "UPDATE \"biblioteka\".publishing SET name = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, publishing.getName());
            statement.setInt(2, publishing.getId());
            execute = statement.execute();
        }
        if (isNotEmpty(publishing.getCity())) {
            String sqlSelect= "UPDATE \"biblioteka\".publishing SET city = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, publishing.getCity());
            statement.setInt(2, publishing.getId());
            execute = statement.execute();
        }
        if (isNotEmpty(publishing.getPhone())) {
            String sqlSelect= "UPDATE \"biblioteka\".publishing SET phone = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, publishing.getPhone());
            statement.setInt(2, publishing.getId());
            execute = statement.execute();
        }
        if (isNotEmpty(publishing.getEmail())) {
            String sqlSelect= "UPDATE \"biblioteka\".publishing SET email = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, publishing.getEmail());
            statement.setInt(2, publishing.getId());
            execute = statement.execute();
        }

        connection.close();
        return execute;
    }

    private boolean isNotEmpty(String string) {
        return !(string == null || string.equals(""));
    }
}
