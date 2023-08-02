package com.app.reactivities.service;

import com.app.reactivities.api.exceptions.ActivitiesNotFoundException;
import com.app.reactivities.model.entity.Activity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService{

    private final EntityManager entityManager;
    @Transactional(readOnly = true)
    @Override
    public List<Activity> retrieveAllActivities() {
        return entityManager.createQuery("SELECT a FROM Activity a", Activity.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Activity findActivityBy(UUID id) {
        var activity = entityManager.find(Activity.class, id);
        if (Objects.isNull(activity)) {
            throw new ActivitiesNotFoundException(id.toString());
        }
        return activity;
    }

    @Override
    public void createActivity(Activity activity) {
        var retrieved = entityManager.find(Activity.class, activity.getId());
        if (Objects.isNull(retrieved)) {
            entityManager.persist(activity);
            return;
        }
        log.info("Activity {} already exist, updating...", activity.getId());
        editActivity(activity.getId(), activity);
    }

    @Override
    public Activity editActivity(UUID id, Activity activity) {
        var retrievedActivity = findActivityBy(id);
        if (Objects.isNull(retrievedActivity)) {
            log.error("Activity with id {} does not exist", id);
        }
        retrievedActivity.setCategory(activity.getCategory());
        retrievedActivity.setCity(activity.getCity());
        retrievedActivity.setDate(activity.getDate());
        retrievedActivity.setDescription(activity.getDescription());
        retrievedActivity.setTitle(activity.getTitle());
        return retrievedActivity;
    }

    @Override
    public void removeActivity(UUID id) {
        var activity = findActivityBy(id);
        if (Objects.nonNull(activity)) {
            entityManager.remove(activity);
        }
        log.info("Activity with id {} does not exist", id);
    }
}
