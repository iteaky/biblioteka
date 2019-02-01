package dao;

import entity.PublishingEntity;
import models.Publishing;

import java.sql.SQLException;
import java.util.List;

public interface PublishingDao {
    PublishingEntity getPublishingById(Integer id) throws SQLException;

    List<PublishingEntity> getAllPublishings() throws SQLException;

    List<PublishingEntity> getPublishingsByIds(List<Integer> ids) throws SQLException;


    PublishingEntity addPublishing(PublishingEntity publishing) throws SQLException;

    boolean deletePublishingById(Integer id) throws SQLException;

    boolean updatePublishing(PublishingEntity publishing) throws SQLException;

}
