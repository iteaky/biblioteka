package service;

import dao.PublishingDao;
import dao.h2_dao_implimintation.PublishingDaoImpl;
import entity.PublishingEntity;
import entity.PublishingEntity;
import models.Publishing;
import models.Publishing;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PublishingService {

    private PublishingDao publishingDao;

    public PublishingService(PublishingDaoImpl publishingDao) {
        this.publishingDao = publishingDao;
    }

    public Publishing getOnePublishing(Integer id) throws SQLException {
        PublishingEntity publishingById = publishingDao.getPublishingById(id);
        return createPublishing(publishingById);
    }

    public List<Publishing> getAll() throws SQLException {
        List<PublishingEntity> allPublishings = publishingDao.getAllPublishings();
        return allPublishings.stream().map(this::createPublishing).collect(Collectors.toList());
    }

    public List<Publishing> getPublishingsByIds(List<Integer> ids) throws SQLException {
        List<PublishingEntity> publishingsByIds = publishingDao.getPublishingsByIds(ids);
        return publishingsByIds.stream().map(this::createPublishing).collect(Collectors.toList());
    }

    public Publishing addPublishing(PublishingEntity publishingEntity) throws SQLException {
        PublishingEntity publishingEntityWithId = publishingDao.addPublishing(publishingEntity);
        return createPublishing(publishingEntityWithId);
    }

    public Boolean deletePublishing(Integer id) throws SQLException {
        return publishingDao.deletePublishingById(id);
    }

    public Publishing updatePublishing(Publishing publishing) throws SQLException {
        PublishingEntity publishingFromDB = publishingDao.getPublishingById(publishing.getId());
        PublishingEntity publishingFromFE = createPublishingEntity(publishing);
        PublishingEntity publishingForUpdate = new PublishingEntity();
        publishingForUpdate.setId(publishing.getId());
        if (!publishingFromDB.getName().equals(publishingFromFE.getName())) {
            publishingForUpdate.setName(publishingFromFE.getName());
        }
        if (!publishingFromDB.getName().equals(publishingForUpdate.getName())) {
            publishingForUpdate.setName(publishingFromFE.getName());
        }
        if (!publishingFromDB.getEmail().equals(publishingFromFE.getEmail())) {
            publishingForUpdate.setEmail(publishingFromFE.getEmail());
        }
        if (publishingFromDB.getCity().equals(publishingFromFE.getCity())) {
            publishingForUpdate.setCity(publishingFromFE.getCity());
        }
        if (publishingFromDB.getPhone().equals(publishingFromFE.getPhone())) {
            publishingForUpdate.setPhone(publishingFromFE.getPhone());
        }
        boolean complete = publishingDao.updatePublishing(publishingForUpdate);
        if (complete) return publishing;
        else throw new RuntimeException("update failed");
    }

    private Publishing createPublishing(PublishingEntity entity) {
        return new Publishing(entity.getId(), entity.getName(), entity.getCity(), entity.getPhone(), entity.getEmail());
    }

    private PublishingEntity createPublishingEntity(Publishing entity) {
        return new PublishingEntity(entity.getId(), entity.getName(), entity.getCity(), entity.getPhone(), entity.getEmail());
    }
}
